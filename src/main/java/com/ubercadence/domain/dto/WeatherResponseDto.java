package com.ubercadence.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDto {

    @JsonAlias("name")
    private String cityName;

    @JsonAlias("main")
    private Map<String, Double> temperatures;

}
