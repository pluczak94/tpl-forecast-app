package com.luczak.tcp.weatherapp.clients;

import com.luczak.tcp.weatherapp.model.ClientApiResponse;

public interface ForecastApiClient {

    ClientApiResponse callApi(double latitude, double longitude);
}
