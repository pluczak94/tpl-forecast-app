package com.luczak.tcp.weatherapp.service;


import com.luczak.tcp.weatherapp.model.ForecastResponse;

public interface ForecastService {

    ForecastResponse getForecastForLocation(double latitude, double longitude);

}
