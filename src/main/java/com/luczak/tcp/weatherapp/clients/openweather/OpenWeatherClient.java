package com.luczak.tcp.weatherapp.clients.openweather;

import com.luczak.tcp.weatherapp.clients.ForecastApiClient;
import com.luczak.tcp.weatherapp.clients.openweather.model.OpenWeatherResponse;
import com.luczak.tcp.weatherapp.clients.openweather.validator.OpenWeatherResponseValidator;
import com.luczak.tcp.weatherapp.config.OpenWeatherConfig;
import com.luczak.tcp.weatherapp.exception.ClientConnectionException;
import com.luczak.tcp.weatherapp.exception.ClientException;
import com.luczak.tcp.weatherapp.exception.ClientResponseException;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
@Slf4j
public class OpenWeatherClient implements ForecastApiClient {

    private final RestTemplate restTemplate;
    private final OpenWeatherConfig config;
    private final OpenWeatherResponseValidator responseValidator;


    @Retry(name = "weatherAPi", fallbackMethod = "onFailure")
    public OpenWeatherResponse getClientApiResponse(double latitude, double longitude) throws RestClientException {

        URI uri = prepareURi(latitude, longitude);
        OpenWeatherResponse response = callApi(uri);
        validateResponse(response);

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

    private OpenWeatherResponse callApi(URI uri) {

        OpenWeatherResponse response;
        try {
            response = restTemplate.getForObject(uri, OpenWeatherResponse.class);
        } catch (RestClientException ex) {
            log.error("Error during OpenWeather call", ex);
            throw new ClientConnectionException(ex);
        }
        log.info("OpenWeatherApi Response: " + response);

        return response;
    }

    private void validateResponse(OpenWeatherResponse response) {

        if (!responseValidator.isValid(response)) {
            throw new ClientResponseException();
        }
    }

    // Signature method is required by resilience4j, method used as fallbackMethod
    private OpenWeatherResponse onFailure(double latitude, double longitude, Throwable e) {
        throw new ClientException(e);
    }
}
