package com.ubercadence.activityworker.service;

import com.ubercadence.activityworker.domain.Weather;

public interface WeatherService {

    Weather takeWeather(String cityNameReq);

    void saveWeather(Weather weather);

}
