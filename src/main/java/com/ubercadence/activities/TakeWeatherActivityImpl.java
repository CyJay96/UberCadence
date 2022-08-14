package com.ubercadence.activities;

import com.ubercadence.domain.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class TakeWeatherActivityImpl implements TakeWeatherActivity {

//    @Autowired
//    private RestTemplate restTemplate;

    @Override
    public WeatherResponseDto takeWeatherFromApi(final String cityNameReq) {
//        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityNameReq +
//                "&APPID=82383c72f2b900e1b80d072c63bc4542";
//
//        return restTemplate.postForObject(url, Collections.emptyList(), WeatherResponseDto.class);

        WeatherResponseDto weatherResponseDto = new WeatherResponseDto();
        weatherResponseDto.setCityName("London1");
        Map<String, Double> main1 = new HashMap<>();
        main1.put("temp1", 25.67);
        weatherResponseDto.setTemperatures(main1);
        return weatherResponseDto;
    }

}
