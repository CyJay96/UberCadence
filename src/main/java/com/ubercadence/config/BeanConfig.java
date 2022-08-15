package com.ubercadence.config;

import com.ubercadence.activities.StoreWeatherActivityImpl;
import com.ubercadence.activities.TakeWeatherActivityImpl;
import com.ubercadence.workflow.WorkflowWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public WorkflowWorker getWorkflowWorker() {
        return new WorkflowWorker();
    }

    @Bean
    public TakeWeatherActivityImpl getTakeWeatherActivityImpl() {
        return new TakeWeatherActivityImpl();
    }

    @Bean
    public StoreWeatherActivityImpl getStoreWeatherActivityImpl() {
        return new StoreWeatherActivityImpl();
    }

}
