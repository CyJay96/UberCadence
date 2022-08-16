package com.ubercadence.service;

import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;
import com.ubercadence.repos.WeatherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate;
    private final WeatherRepo weatherRepo;

    @Value("${openweathermap.appid}")
    private String weather_appid;

    public Weather takeWeather(String cityNameReq) {
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

    public Page<Weather> findWeather(Pageable pageable) {
        return weatherRepo.findAll(pageable);
    }

}
