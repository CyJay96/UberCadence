package com.ubercadence.workflow;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Async;
import com.uber.cadence.workflow.Promise;
import com.uber.cadence.workflow.Workflow;
import com.ubercadence.activities.StoreWeatherActivity;
import com.ubercadence.activities.TakeWeatherActivity;
import com.ubercadence.domain.Weather;

import java.time.Duration;

@SuppressWarnings("ALL")
public class WeatherWorkflowImpl implements WeatherWorkflow {

    private final ActivityOptions options = new ActivityOptions.Builder()
            .setScheduleToCloseTimeout(Duration.ofHours(1))
            .build();

    private final TakeWeatherActivity takeWeatherActivity = Workflow.newActivityStub(TakeWeatherActivity.class, options);
    private final StoreWeatherActivity storeWeatherActivity = Workflow.newActivityStub(StoreWeatherActivity.class, options);

    @Override
    public Weather getWeather(String cityNameReq) {
        Promise<Weather> weatherPromise =
                Async.function(takeWeatherActivity::takeWeatherFromApi, cityNameReq);

        storeWeatherActivity.saveWeather(weatherPromise.get());

        return weatherPromise.get();
    }

}
