package com.ubercadence.activities;

import com.ubercadence.domain.Weather;
import com.ubercadence.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("ALL")
@RequiredArgsConstructor
public class TakeWeatherActivityImpl implements TakeWeatherActivity {

    private final WeatherService weatherService;

    @Override
    public Weather takeWeatherFromApi(final String cityNameReq) {
        return weatherService.takeWeather(cityNameReq);
    }

}
