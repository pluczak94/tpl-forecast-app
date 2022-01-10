package com.luczak.tcp.weatherapp.service.impl;

import com.luczak.tcp.weatherapp.clients.ForecastApiClient;
import com.luczak.tcp.weatherapp.clients.ForecastConverter;
import com.luczak.tcp.weatherapp.clients.openweather.model.OpenWeatherResponse;
import com.luczak.tcp.weatherapp.model.ForecastResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ForecastServiceImplTest {

    @InjectMocks
    private ForecastServiceImpl forecastService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private ForecastApiClient foreCastApiClient;
    @Mock
    private ForecastConverter forecastConverter;

    @Test
    void getForecastForLocation() {
        ForecastResponse expectedResponse = new ForecastResponse();
        when(foreCastApiClient.getClientApiResponse(1, 1)).thenReturn(new OpenWeatherResponse());
        when(forecastConverter.convertToResponse(any())).thenReturn(expectedResponse);

        ForecastResponse forecastForLocation = forecastService.getForecastForLocation(1, 1);

        assertEquals(expectedResponse, forecastForLocation);
    }

}