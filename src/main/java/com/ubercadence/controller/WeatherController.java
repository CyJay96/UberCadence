package com.ubercadence.controller;

import com.ubercadence.domain.Weather;
import com.ubercadence.workflow.WorkflowStarter;
import com.ubercadence.service.WeatherService;
import com.ubercadence.workflow.WorkflowWorker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private final WeatherService weatherService;
    private final WorkflowWorker workflowWorker;

    public WeatherController(WeatherService weatherService, WorkflowWorker workflowWorker) {
        this.weatherService = weatherService;
        this.workflowWorker = workflowWorker;
    }

    @GetMapping("/weather")
    public String findWeather(Model model) {
        Iterable<Weather> weatherList = weatherService.findWeather();
        model.addAttribute("weatherList", weatherList);
        return "weather";
    }

    @PostMapping("/weather")
    public String addWeather(@RequestParam String cityNameReq) {
        WorkflowStarter.startWorkflowStarter(cityNameReq);
        workflowWorker.startWorkflowWorker();
        return "redirect:/weather";
    }

}
