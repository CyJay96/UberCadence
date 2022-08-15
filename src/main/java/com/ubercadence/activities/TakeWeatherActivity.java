package com.ubercadence.activities;

import com.ubercadence.domain.Weather;

public interface TakeWeatherActivity {

    Weather takeWeatherFromApi(String cityNameReq);

}
