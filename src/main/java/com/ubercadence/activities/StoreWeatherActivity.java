package com.ubercadence.activities;

import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;

public interface StoreWeatherActivity {

    void saveWeather(WeatherResponseDto weather);

}
