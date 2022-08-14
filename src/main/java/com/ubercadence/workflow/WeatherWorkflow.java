package com.ubercadence.workflow;

import com.uber.cadence.workflow.WorkflowMethod;
import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;

public interface WeatherWorkflow {

    //todo @Value
    @WorkflowMethod(executionStartToCloseTimeoutSeconds = 300, taskList = "cadence-weather-taskList")
    WeatherResponseDto getWeather(String cityNameReq);

}
