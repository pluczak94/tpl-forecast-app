package com.luczak.tcp.weatherapp.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ForecastResponse implements Serializable {

    private double currentTemp;
    private int currentHumidity;
    private List<ForecastDay> forecastDays;

}
