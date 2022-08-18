package com.ubercadence.workflowlauncher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.ubercadence"})
@EntityScan(basePackages = {"com.ubercadence"})
@ComponentScan(basePackages = {"com.ubercadence"})
public class WorkflowLauncherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowLauncherApplication.class, args);
    }

}
