package com.luczak.tcp.weatherapp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@Configuration
@Validated
@ConfigurationProperties(prefix = "open-weather.config")
public class OpenWeatherConfig {

    @NotBlank
    private String url;
    @NotBlank
    private String units;
    private String exclude;
    @NotBlank
    private String apiKey;
}

