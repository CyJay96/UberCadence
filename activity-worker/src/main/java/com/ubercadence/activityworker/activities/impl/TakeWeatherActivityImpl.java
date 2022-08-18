package com.ubercadence.activityworker.activities.impl;

import com.ubercadence.activityworker.activities.TakeWeatherActivity;
import com.ubercadence.activityworker.domain.Weather;
import com.ubercadence.activityworker.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("ALL")
@RequiredArgsConstructor
public class TakeWeatherActivityImpl implements TakeWeatherActivity {

    private final WeatherService weatherService;

    @Override
    public Weather takeWeatherFromApi(final String city) {
        return weatherService.takeWeather(city);
    }

}
