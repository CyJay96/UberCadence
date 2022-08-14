package com.ubercadence.activities;

import com.ubercadence.domain.dto.WeatherResponseDto;

public interface TakeWeatherActivity {

    WeatherResponseDto takeWeatherFromApi(String cityNameReq);

}
