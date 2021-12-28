package com.luczak.tcp.weatherapp.service.impl;

import com.luczak.tcp.weatherapp.clients.ForecastApiClient;
import com.luczak.tcp.weatherapp.clients.ForecastConverter;
import com.luczak.tcp.weatherapp.model.ClientApiResponse;
import com.luczak.tcp.weatherapp.model.ForecastResponse;
import com.luczak.tcp.weatherapp.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForecastServiceImpl implements ForecastService {

    private final ForecastApiClient foreCastApiClient;
    private final ForecastConverter forecastConverter;

    @Override
    public ForecastResponse getForecastForLocation(double latitude, double longitude) {

        ClientApiResponse apiResponse = foreCastApiClient.callApi(latitude, longitude);

        return forecastConverter.convertToResponse(apiResponse);
    }

}
