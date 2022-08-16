package com.ubercadence.activities;

import com.ubercadence.domain.Weather;
import com.ubercadence.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("ALL")
@RequiredArgsConstructor
public class StoreWeatherActivityImpl implements StoreWeatherActivity {

    private final WeatherService weatherService;

    @Override
    public void saveWeather(final Weather weather) {
        weatherService.save(weather);
    }

}
