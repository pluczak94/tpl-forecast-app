package com.luczak.tcp.weatherapp.exception;

public class OpenWeatherClientException extends ClientException {

    public OpenWeatherClientException(Throwable cause) {
        super(cause);
    }

    public OpenWeatherClientException(String message) {
        super(message);
    }
}
