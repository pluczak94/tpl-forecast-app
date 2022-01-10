package com.luczak.tcp.weatherapp.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ForecastDay implements Serializable {

    private LocalDate day;
    private double tempDay;
    private double tempNight;
    private int humidity;
}
