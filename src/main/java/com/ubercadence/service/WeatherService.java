package com.ubercadence.service;

import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;
import com.ubercadence.repos.WeaterRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeaterRepo weaterRepo;

    public WeatherService(RestTemplate restTemplate, WeaterRepo weaterRepo) {
        this.restTemplate = restTemplate;
        this.weaterRepo = weaterRepo;
    }


    public void saveWeather(String cityNameReq) {

        // second way
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityNameReq + "&APPID=82383c72f2b900e1b80d072c63bc4542";
        WeatherResponseDto weatherResponseDto = restTemplate
                .postForObject(url, Collections.emptyList(), WeatherResponseDto.class);

        // storing in a DB
        if (weatherResponseDto != null) {
            String cityName = weatherResponseDto.getCityName();
            double temperature = weatherResponseDto.getTemperatures().get("temp") - 273.15;
            temperature = ((double) ((int) (temperature * 100))) / 100;
            String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));

            Weather weather = new Weather(cityName, temperature, creationDate);

            weaterRepo.save(weather);
        }
    }

    public Iterable<Weather> findWeather() {
        return weaterRepo.findAll();
    }

}
