package com.luczak.tcp.weatherapp.exception;

import org.springframework.web.client.RestClientException;

public class ClientConnectionException extends RuntimeException {

    public ClientConnectionException(RestClientException ex) {
        super(ex);
    }
}
