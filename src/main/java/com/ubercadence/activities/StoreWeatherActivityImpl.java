package com.ubercadence.activities;

import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;
import com.ubercadence.repos.WeaterRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class StoreWeatherActivityImpl implements StoreWeatherActivity {

    @Override
    public void saveWeather(final WeatherResponseDto weather) {
        System.out.println("Weather saved in a DB");
    }

}
