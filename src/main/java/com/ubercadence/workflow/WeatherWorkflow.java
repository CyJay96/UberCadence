package com.ubercadence.workflow;

import com.uber.cadence.workflow.WorkflowMethod;
import com.ubercadence.domain.Weather;

public interface WeatherWorkflow {

    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 300, taskList = "default_task_list")
    Weather getWeather(String cityNameReq);

}
