package com.luczak.tcp.weatherapp.clients;

import com.luczak.tcp.weatherapp.model.ClientApiResponse;
import org.springframework.cloud.sleuth.annotation.NewSpan;

public interface ForecastApiClient {

    @NewSpan
    ClientApiResponse getClientApiResponse(double latitude, double longitude);
}
