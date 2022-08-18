package com.ubercadence.activityworker.service.impl;

import com.ubercadence.activityworker.config.WeatherWebClient;
import com.ubercadence.activityworker.domain.Weather;
import com.ubercadence.activityworker.domain.dto.WeatherResponseDto;
import com.ubercadence.activityworker.repository.WeatherRepo;
import com.ubercadence.activityworker.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    private final WeatherWebClient weatherWebClient;
    private final WeatherRepo weatherRepo;

    @Override
    public Weather takeWeather(String cityNameReq) {
        WeatherResponseDto weatherResponseDto = weatherWebClient.getWeatherFromWebClient(cityNameReq).block();

        if (weatherResponseDto != null) {
            String cityName = weatherResponseDto.getCityName();
            double temperature = weatherResponseDto.getTemperatures().get("temp") - 273.15;
            temperature = ((double) ((int) (temperature * 100))) / 100;
            String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));

            return new Weather(cityName, temperature, creationDate);
        }

        return null;
    }

    @Override
    public void saveWeather(Weather weather) {
        weatherRepo.save(weather);

    }

}
