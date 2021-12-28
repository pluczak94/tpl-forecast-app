package com.luczak.tcp.weatherapp.clients.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.luczak.tcp.weatherapp.model.ClientApiResponse;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResponse extends ClientApiResponse {

    private String timezone;
    private CurrentWeather current;
    private List<DailyWeather> daily;

}
