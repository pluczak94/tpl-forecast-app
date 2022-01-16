package com.luczak.tcp.weatherapp.exception;

public class ClientResponseException extends RuntimeException {

    public static final String MESSAGE = "Invalid response from OpenWeather Api";

    public ClientResponseException() {
        super(MESSAGE);
    }
}
