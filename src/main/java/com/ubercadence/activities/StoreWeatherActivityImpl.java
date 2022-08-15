package com.ubercadence.activities;

import com.ubercadence.domain.Weather;
import com.ubercadence.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("ALL")
public class StoreWeatherActivityImpl implements StoreWeatherActivity {

    @Autowired
    private WeatherService weatherService;

    @Override
    public void saveWeather(final Weather weather) {
        weatherService.save(weather);
        System.out.println("Weather saved in a DB");
    }

}
