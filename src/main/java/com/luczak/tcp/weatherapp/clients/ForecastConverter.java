package com.luczak.tcp.weatherapp.clients;

import com.luczak.tcp.weatherapp.model.ClientApiResponse;
import com.luczak.tcp.weatherapp.model.ForecastResponse;

public interface ForecastConverter<T extends ClientApiResponse> {
    ForecastResponse convertToResponse(T clientApiResponse);
}
