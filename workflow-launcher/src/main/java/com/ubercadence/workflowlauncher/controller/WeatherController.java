package com.ubercadence.workflowlauncher.controller;

import com.ubercadence.activityworker.domain.Weather;
import com.ubercadence.activityworker.service.WeatherService;
import com.ubercadence.workflowlauncher.service.WorkflowStarter;
import com.ubercadence.workflowlauncher.service.WorkflowWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WorkflowStarter workflowStarter;
    private final WorkflowWorker workflowWorker;
    private final WeatherService weatherService;

    @GetMapping("/weather/{city}")
    public Weather getWeather(@PathVariable String city) {
        workflowStarter.startWorkflowStarter(city);
        workflowWorker.startWorkflowWorker();
        return weatherService.takeWeather(city);
    }

}
