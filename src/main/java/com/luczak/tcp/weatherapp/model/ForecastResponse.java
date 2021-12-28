package com.luczak.tcp.weatherapp.model;

import lombok.Data;

import java.util.List;

@Data
public class ForecastResponse {

    private double currentTemp;
    private int currentHumidity;
    private List<ForecastDay> forecastDays;

}
