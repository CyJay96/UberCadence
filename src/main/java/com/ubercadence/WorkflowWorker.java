package com.ubercadence;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerFactory;
import com.ubercadence.activities.StoreWeatherActivity;
import com.ubercadence.activities.StoreWeatherActivityImpl;
import com.ubercadence.activities.TakeWeatherActivity;
import com.ubercadence.activities.TakeWeatherActivityImpl;
import com.ubercadence.workflow.WeatherWorkflowImpl;

public class WorkflowWorker {

    //todo @Value
    private static final String DOMAIN = "samples-domain";
    private static final String DEFAULT_TASK_LIST = "cadence-weather-taskList";

    @SuppressWarnings("CatchAndPrintStackTrace")
    public static void main(String[] args) {
        WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                        WorkflowClientOptions.newBuilder()
                                .setDomain(DOMAIN)
                                .build());

        WorkerFactory factory = WorkerFactory.newInstance(workflowClient);
        final Worker workerForCommonTaskList = factory.newWorker(DEFAULT_TASK_LIST);
        workerForCommonTaskList.registerWorkflowImplementationTypes(WeatherWorkflowImpl.class);

        TakeWeatherActivity takeWeatherActivity = new TakeWeatherActivityImpl();
        StoreWeatherActivity storeWeatherActivity = new StoreWeatherActivityImpl();
        workerForCommonTaskList.registerActivitiesImplementations(takeWeatherActivity, storeWeatherActivity);

        // Start all workers created by this factory.
        factory.start();
        System.out.println("Worker started for task list: " + DEFAULT_TASK_LIST);
    }

}
