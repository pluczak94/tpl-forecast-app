package com.luczak.tcp.weatherapp.controller;

import com.luczak.tcp.weatherapp.model.ForecastResponse;
import com.luczak.tcp.weatherapp.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping(value = "/forecast", produces = "application/json")
    public ForecastResponse getCurrentWeather(@RequestParam double latitude, @RequestParam double longitude) {

        return forecastService.getForecastForLocation(latitude, longitude);
    }

}