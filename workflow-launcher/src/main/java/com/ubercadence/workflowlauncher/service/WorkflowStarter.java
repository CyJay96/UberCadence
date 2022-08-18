package com.ubercadence.workflowlauncher.service;

import com.uber.cadence.WorkflowExecution;
import com.uber.cadence.WorkflowIdReusePolicy;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.client.WorkflowOptions;
import com.uber.cadence.serviceclient.ClientOptions;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import com.ubercadence.workflowworker.workflow.WeatherWorkflow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WorkflowStarter {

    @Value("${cadence.domain}")
    private String domain;

    @Value("${cadence.default-task-list}")
    private String default_task_list;

    @Value("${cadence.workflow-id}")
    private String workflow_id;

    @SuppressWarnings("CatchAndPrintStackTrace")
    public void startWorkflowStarter(String city) {
        final WorkflowClient workflowClient =
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

        WeatherWorkflow getWeatherWorkflow = workflowClient.newWorkflowStub(WeatherWorkflow.class, workflowOptions);

        WorkflowExecution execution = WorkflowClient.start(getWeatherWorkflow::getWeather, city);
        System.out.println("started workflow execution" + execution);
    }

}
