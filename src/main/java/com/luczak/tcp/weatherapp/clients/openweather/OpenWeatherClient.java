package com.luczak.tcp.weatherapp.clients.openweather;

import com.luczak.tcp.weatherapp.clients.ForecastApiClient;
import com.luczak.tcp.weatherapp.clients.openweather.model.OpenWeatherResponse;
import com.luczak.tcp.weatherapp.config.OpenWeatherConfig;
import com.luczak.tcp.weatherapp.exception.OpenWeatherClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OpenWeatherClient implements ForecastApiClient {

    private final RestTemplate restTemplate;
    private final OpenWeatherConfig config;


    public OpenWeatherResponse callApi(double latitude, double longitude) {

        URI uri = prepareURi(latitude, longitude);
        OpenWeatherResponse response;
        try {
            response = restTemplate.getForObject(uri, OpenWeatherResponse.class);
        } catch (RestClientException ex) {
            throw new OpenWeatherClientException(ex);
        }

        if (Objects.isNull(response)) {
            throw new OpenWeatherClientException("Null response from OpenWeather Api");
        }

        return response;
    }

    private URI prepareURi(double latitude, double longitude) {

        return UriComponentsBuilder
                .fromHttpUrl(config.getUrl())
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("units", config.getUnits())
                .queryParam("exclude", config.getExclude())
                .queryParam("appId", config.getApiKey())
                .build()
                .toUri();
    }
}
