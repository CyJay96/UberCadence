package com.ubercadence.workflow;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Async;
import com.uber.cadence.workflow.Promise;
import com.uber.cadence.workflow.Workflow;
import com.ubercadence.activities.StoreWeatherActivity;
import com.ubercadence.activities.TakeWeatherActivity;
import com.ubercadence.domain.Weather;
import com.ubercadence.domain.dto.WeatherResponseDto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("ALL")
public class WeatherWorkflowImpl implements WeatherWorkflow {

    private final ActivityOptions options = new ActivityOptions.Builder()
            .setScheduleToCloseTimeout(Duration.ofHours(1))
            .build();

    private final TakeWeatherActivity takeWeatherActivity = Workflow.newActivityStub(TakeWeatherActivity.class, options);
    private final StoreWeatherActivity storeWeatherActivity = Workflow.newActivityStub(StoreWeatherActivity.class, options);

    @Override
    public WeatherResponseDto getWeather(String cityNameReq) {
        Promise<WeatherResponseDto> weatherResponseDtoPromise =
                Async.function(takeWeatherActivity::takeWeatherFromApi, cityNameReq);

        storeWeatherActivity.saveWeather(weatherResponseDtoPromise.get());

        return weatherResponseDtoPromise.get();
    }

//    @Override
//    public WeatherResponseDto getWeather(String cityNameReq) {
//        Promise<WeatherResponseDto> weatherResponseDtoPromise =
//                Async.function(takeWeatherActivity::takeWeatherFromApi, cityNameReq);
//        System.out.println(weatherResponseDtoPromise);
//
//        WeatherResponseDto weatherResponseDto = weatherResponseDtoPromise.get();
//
//        if (weatherResponseDto != null) {
//            String cityName = weatherResponseDto.getCityName();
//            double temperature = weatherResponseDto.getTemperatures().get("temp") - 273.15;
//            temperature = ((double) ((int) (temperature * 100))) / 100;
//            String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss"));
//
//            Weather weather = new Weather(cityName, temperature, creationDate);
//            System.out.println(weather);
//
//            storeWeatherActivity.saveWeather(weather);
//        }
//
//        return weatherResponseDto;
//    }

}
