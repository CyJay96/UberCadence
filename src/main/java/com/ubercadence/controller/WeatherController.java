package com.ubercadence.controller;

import com.ubercadence.domain.Weather;
import com.ubercadence.service.WeatherService;
import com.ubercadence.workflow.WorkflowWorker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final WorkflowWorker workflowWorker;

    @GetMapping("/weather")
    public String findWeather(Model model,
                              @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Weather> weatherPage = weatherService.findWeather(pageable);
        model.addAttribute("weatherPage", weatherPage);
        model.addAttribute("url", "/weather");
        return "weather";
    }

    @PostMapping("/weather")
    public String addWeather(@RequestParam String cityNameReq) {
        if (cityNameReq != null && !cityNameReq.isEmpty()) {
            workflowWorker.startWorkflowWorker(cityNameReq);
        }
        return "redirect:/weather";
    }

}
