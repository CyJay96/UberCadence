package com.ubercadence.workflowworker.workflow;

import com.uber.cadence.workflow.WorkflowMethod;
import com.ubercadence.activityworker.domain.Weather;

public interface WeatherWorkflow {

    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 300, taskList = "default_task_list")
    Weather getWeather(String city);

}
