package com.ubercadence.activityworker.activities.impl;

import com.ubercadence.activityworker.activities.StoreWeatherActivity;
import com.ubercadence.activityworker.domain.Weather;
import com.ubercadence.activityworker.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("ALL")
@RequiredArgsConstructor
public class StoreWeatherActivityImpl implements StoreWeatherActivity {

    private final WeatherService weatherService;

    @Override
    public void saveWeather(final Weather weather) {
        weatherService.saveWeather(weather);
    }

}
