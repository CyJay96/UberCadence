package com.ubercadence.workflow;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.WorkflowIdReusePolicy;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.client.WorkflowOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import com.ubercadence.activities.StoreWeatherActivity;
import com.ubercadence.activities.TakeWeatherActivity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class WorkflowWorker {

    @Value("${cadence.domain}")
    private String domain;

    @Value("${cadence.default-task-list}")
    private String default_task_list;

    @Value("${cadence.workflow-id}")
    private String workflow_id;

    private final TakeWeatherActivity takeWeatherActivity;
    private final StoreWeatherActivity storeWeatherActivity;

    private WeatherWorkflow getWeatherWorkflow;
    private WorkerFactory factory;

    public WorkflowWorker(TakeWeatherActivity takeWeatherActivity, StoreWeatherActivity storeWeatherActivity) {
        this.takeWeatherActivity = takeWeatherActivity;
        this.storeWeatherActivity = storeWeatherActivity;
    }

    @EventListener(ApplicationReadyEvent.class)
    @SuppressWarnings("CatchAndPrintStackTrace")
    public void startWorkflowWorker() {
        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                        WorkflowClientOptions.newBuilder()
                                .setDomain(domain)
                                .build());

        WorkflowOptions workflowOptions =
                new WorkflowOptions.Builder()
                        .setTaskList(default_task_list)
                        .setWorkflowId(workflow_id)
                        .setWorkflowIdReusePolicy(WorkflowIdReusePolicy.AllowDuplicate)
                        .build();

        getWeatherWorkflow = workflowClient.newWorkflowStub(WeatherWorkflow.class, workflowOptions);

        factory = WorkerFactory.newInstance(workflowClient);
        final Worker workerForCommonTaskList = factory.newWorker(default_task_list);
        workerForCommonTaskList.registerWorkflowImplementationTypes(WeatherWorkflowImpl.class);

        workerForCommonTaskList.registerActivitiesImplementations(takeWeatherActivity, storeWeatherActivity);
    }

    public void start(String cityNameReq) {
        WorkflowExecution execution = WorkflowClient.start(getWeatherWorkflow::getWeather, cityNameReq);
        System.out.println("started workflow execution" + execution);
        factory.start();
        System.out.println("Worker started for task list: " + default_task_list);
    }

}
