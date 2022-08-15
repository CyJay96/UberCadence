package com.ubercadence.workflow;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import com.ubercadence.activities.StoreWeatherActivityImpl;
import com.ubercadence.activities.TakeWeatherActivityImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowWorker {

    private static final String DOMAIN = "weather-domain";
    private static final String DEFAULT_TASK_LIST = "cadence-weather-taskList";

    @Autowired
    private TakeWeatherActivityImpl takeWeatherActivity;

    @Autowired
    private StoreWeatherActivityImpl storeWeatherActivity;

    @SuppressWarnings("CatchAndPrintStackTrace")
    public void startWorkflowWorker() {
        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                        WorkflowClientOptions.newBuilder().setDomain(DOMAIN).build());

        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        final Worker workerForCommonTaskList = factory.newWorker(DEFAULT_TASK_LIST);
        workerForCommonTaskList.registerWorkflowImplementationTypes(WeatherWorkflowImpl.class);

        workerForCommonTaskList.registerActivitiesImplementations(takeWeatherActivity, storeWeatherActivity);

        factory.start();
        System.out.println("Worker started for task list: " + DEFAULT_TASK_LIST);
    }

}
