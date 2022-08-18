package com.ubercadence.activityworker.service;

import com.ubercadence.activityworker.domain.Weather;

public interface WeatherService {

    public Weather takeWeather(String cityNameReq);

    public void saveWeather(Weather weather);

}
