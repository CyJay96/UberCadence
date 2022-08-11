package com.ubercadence.controller;

import com.ubercadence.domain.Weather;
import com.ubercadence.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public String findWeather(Model model) {
        Iterable<Weather> weatherList = weatherService.findWeather();
        model.addAttribute("weatherList", weatherList);
        return "weather";
    }

    @PostMapping("/weather")
    public String addWeather(@RequestParam String cityNameReq) {
        weatherService.saveWeather(cityNameReq);
        return "redirect:/weather";
    }

}
