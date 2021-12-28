package com.luczak.tcp.weatherapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ForecastDay {
    private LocalDate day;
    private double tempDay;
    private double tempNight;
    private int humidity;
}
