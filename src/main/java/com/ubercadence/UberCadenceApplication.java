package com.ubercadence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UberCadenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UberCadenceApplication.class, args);
    }

}
