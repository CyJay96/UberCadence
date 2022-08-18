package com.ubercadence.workflowlauncher.service;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import com.ubercadence.activityworker.activities.impl.StoreWeatherActivityImpl;
import com.ubercadence.activityworker.activities.impl.TakeWeatherActivityImpl;
import com.ubercadence.workflowworker.workflow.impl.WeatherWorkflowImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkflowWorker {

    @Value("${cadence.domain}")
    private String domain;

    @Value("${cadence.default-task-list}")
    private String default_task_list;

    private final TakeWeatherActivityImpl takeWeatherActivity;
    private final StoreWeatherActivityImpl storeWeatherActivity;

    public void startWorkflowWorker() {
        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                        WorkflowClientOptions.newBuilder()
                                .setDomain(domain)
                                .build());


        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        final Worker workerForCommonTaskList = factory.newWorker(default_task_list);

        workerForCommonTaskList.registerWorkflowImplementationTypes(WeatherWorkflowImpl.class);
        workerForCommonTaskList.registerActivitiesImplementations(takeWeatherActivity, storeWeatherActivity);

        factory.start();
        System.out.println("Worker started for task list: " + default_task_list);
    }

}
