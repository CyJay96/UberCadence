package com.ubercadence.workflowworker.workflow.impl;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Async;
import com.uber.cadence.workflow.Promise;
import com.uber.cadence.workflow.Workflow;
import com.ubercadence.activityworker.activities.StoreWeatherActivity;
import com.ubercadence.activityworker.activities.TakeWeatherActivity;
import com.ubercadence.activityworker.domain.Weather;
import com.ubercadence.workflowworker.workflow.WeatherWorkflow;

import java.time.Duration;

@SuppressWarnings("ALL")
public class WeatherWorkflowImpl implements WeatherWorkflow {

    private final ActivityOptions options = new ActivityOptions.Builder()
            .setScheduleToCloseTimeout(Duration.ofHours(1))
            .build();

    private final TakeWeatherActivity takeWeatherActivity = Workflow.newActivityStub(TakeWeatherActivity.class, options);
    private final StoreWeatherActivity storeWeatherActivity = Workflow.newActivityStub(StoreWeatherActivity.class, options);

    @Override
    public Weather getWeather(String city) {
        Promise<Weather> weatherPromise = Async.function(takeWeatherActivity::takeWeatherFromApi, city);

        storeWeatherActivity.saveWeather(weatherPromise.get());

        return weatherPromise.get();
    }

}
