package com.ubercadence.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "data", url = "https://api.openweathermap.org/data/2.5")
public interface IWeatherClient {

    @RequestMapping(method = RequestMethod.GET, value = "/weather?q={q}&APPID=82383c72f2b900e1b80d072c63bc4542")
    ResponseEntity<Map> getWeather(@PathVariable("q") String q);

}
