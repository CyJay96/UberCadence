package com.ubercadence;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.WorkflowIdReusePolicy;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.client.WorkflowOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.ubercadence.workflow.WeatherWorkflow;

public class WorkflowStarter {

    //todo @Value
    private static final String DOMAIN = "samples-domain";
    private static final String DEFAULT_TASK_LIST = "cadence-weather-taskList";
    private static final String WORKFLOW_ID = "test-workflow-id";

    @SuppressWarnings("CatchAndPrintStackTrace")
    public static void main(String[] args) {
        final WorkflowClient workflowClient =
                WorkflowClient.newInstance(
                        new WorkflowServiceTChannel(ClientOptions.defaultInstance()),
                        WorkflowClientOptions.newBuilder()
                                .setDomain(DOMAIN)
                                .build());

        WorkflowOptions workflowOptions =
                new WorkflowOptions.Builder()
                        .setTaskList(DEFAULT_TASK_LIST)
                        .setWorkflowId(WORKFLOW_ID)
                        .setWorkflowIdReusePolicy(WorkflowIdReusePolicy.AllowDuplicate)
                        .build();

        WeatherWorkflow getWeatherWorkflow = workflowClient.newWorkflowStub(WeatherWorkflow.class, workflowOptions);

        WorkflowExecution execution = WorkflowClient.start(getWeatherWorkflow::getWeather, "London");
        System.out.println("started workflow execution" + execution);
        System.exit(0);
    }

}
