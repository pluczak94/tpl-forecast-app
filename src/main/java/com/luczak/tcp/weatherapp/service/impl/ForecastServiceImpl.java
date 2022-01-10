package com.luczak.tcp.weatherapp.service.impl;

import com.luczak.tcp.weatherapp.clients.ForecastApiClient;
import com.luczak.tcp.weatherapp.clients.ForecastConverter;
import com.luczak.tcp.weatherapp.model.ForecastResponse;
import com.luczak.tcp.weatherapp.service.ForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

    private final ForecastApiClient foreCastApiClient;
    private final ForecastConverter forecastConverter;

    @Override
    @Cacheable("forecast")
    public ForecastResponse getForecastForLocation(double latitude, double longitude) {

        var apiResponse = foreCastApiClient.getClientApiResponse(latitude, longitude);

        return forecastConverter.convertToResponse(apiResponse);
    }

}
