package com.ubercadence.service;

import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;
import com.ubercadence.repos.WeatherRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherRepo weatherRepo;

    public WeatherService(RestTemplate restTemplate, WeatherRepo weatherRepo) {
        this.restTemplate = restTemplate;
        this.weatherRepo = weatherRepo;
    }

    public Weather takeWeather(String cityNameReq) {
        String weather_appid = "82383c72f2b900e1b80d072c63bc4542";

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityNameReq + "&APPID=" + weather_appid;
        WeatherResponseDto weatherResponseDto = restTemplate
                .postForObject(url, Collections.emptyList(), WeatherResponseDto.class);

        if (weatherResponseDto != null) {
            String cityName = weatherResponseDto.getCityName();
            double temperature = weatherResponseDto.getTemperatures().get("temp") - 273.15;
            temperature = ((double) ((int) (temperature * 100))) / 100;
            String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));

            return new Weather(cityName, temperature, creationDate);
        }

        return null;
    }

    public void save(Weather weather) {
        weatherRepo.save(weather);
    }

    public Iterable<Weather> findWeather() {
        return weatherRepo.findAll();
    }

}
