package com.ubercadence.service;

import com.ubercadence.config.WeatherWebClient;
import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;
import com.ubercadence.repos.WeatherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherWebClient weatherWebClient;
    private final WeatherRepo weatherRepo;

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

    public void save(Weather weather) {
        weatherRepo.save(weather);
    }

    public Page<Weather> findWeather(Pageable pageable) {
        return weatherRepo.findAll(pageable);
    }

}
