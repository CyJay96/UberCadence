package com.ubercadence.config;

import com.ubercadence.domain.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WeatherWebClient {

    private final WebClient client;

    @Value("${host}")
    private String host;

    @Value("${server.port}")
    private String port;

    @Value("${openweathermap.appid}")
    private String weather_appid;

    public WeatherWebClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://" + host + ":" + port).build();
    }

    public Mono<WeatherResponseDto> getWeatherFromWebClient(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" +
                city +
                "&APPID=" +
                weather_appid;

        return this.client.get().uri(url).accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(WeatherResponseDto.class);
    }

}
