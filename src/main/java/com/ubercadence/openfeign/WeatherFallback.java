package com.ubercadence.openfeign;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Component
public class WeatherFallback implements IWeatherClient {

    @Override
    public ResponseEntity<Map> getWeather(@PathVariable("q") String q) {
        return null;
    }

}
