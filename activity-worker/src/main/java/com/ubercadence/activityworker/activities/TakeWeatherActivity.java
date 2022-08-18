package com.ubercadence.activityworker.activities;

import com.ubercadence.activityworker.domain.Weather;

public interface TakeWeatherActivity {

    Weather takeWeatherFromApi(String city);

}
