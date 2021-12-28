package com.luczak.tcp.weatherapp.clients.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyTemperature {

    private double day;
    private double night;
}
