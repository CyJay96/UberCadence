package com.ubercadence.service;

import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;
import com.ubercadence.openfeign.IWeatherClient;
import com.ubercadence.repos.WeaterRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Service
public class WeatherService {

    private final IWeatherClient weatherClient;
    private final RestTemplate restTemplate;
    private final WeaterRepo weaterRepo;

    public WeatherService(IWeatherClient weatherClient, RestTemplate restTemplate, WeaterRepo weaterRepo) {
        this.weatherClient = weatherClient;
        this.restTemplate = restTemplate;
        this.weaterRepo = weaterRepo;
    }


    public void saveWeather(String cityNameReq) {
//        ResponseEntity<Map> ok = ResponseEntity.ok(weatherClient.getWeather(cityNameReq).getBody());
//
//        String cityNameResp = (String) ok.getBody().get("name");
//        Map<String, Double> main = (Map<String, Double>) ok.getBody().get("main");
//        Double tempResp = main.get("temp");


        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityNameReq + "&APPID=82383c72f2b900e1b80d072c63bc4542";
        WeatherResponseDto weatherResponseDto = restTemplate
                .postForObject(url, Collections.emptyList(), WeatherResponseDto.class);

        if (weatherResponseDto != null) {
            String cityName = weatherResponseDto.getCityName();
            double temperature = weatherResponseDto.getTemperatures().get("temp") - 273.15;
            String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));

            Weather weather = new Weather(cityName, temperature, creationDate);

            weaterRepo.save(weather);
        }
    }

    public Iterable<Weather> findWeather() {
        return weaterRepo.findAll();
    }

}
