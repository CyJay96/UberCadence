package com.ubercadence.activities;

import com.ubercadence.domain.Weather;
import com.ubercadence.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("ALL")
public class TakeWeatherActivityImpl implements TakeWeatherActivity {

    @Autowired
    private WeatherService weatherService;

    @Override
    public Weather takeWeatherFromApi(final String cityNameReq) {
        return weatherService.takeWeather(cityNameReq);
    }

}
