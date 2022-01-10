package com.luczak.tcp.weatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ForecastApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForecastApplication.class, args);
    }

}
