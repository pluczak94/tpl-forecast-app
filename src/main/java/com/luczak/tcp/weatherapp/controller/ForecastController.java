package com.luczak.tcp.weatherapp.controller;

import com.luczak.tcp.weatherapp.annotations.ValidLatitude;
import com.luczak.tcp.weatherapp.annotations.ValidLongitude;
import com.luczak.tcp.weatherapp.model.ForecastResponse;
import com.luczak.tcp.weatherapp.service.ForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@CrossOrigin
@Validated
@Slf4j
@RequestMapping("/api")
public class ForecastController {

    private final ForecastService forecastService;

    @GetMapping(value = "/currentForecast", produces = "application/json")
    public ForecastResponse getCurrentWeather(@RequestParam @ValidLatitude double latitude, @RequestParam @ValidLongitude double longitude) {

        log.info("CurrentForecast Endpoint, latitude: {} longitude: {}", latitude, longitude);

        return forecastService.getForecastForLocation(latitude, longitude);
    }

}