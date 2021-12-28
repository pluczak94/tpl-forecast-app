package com.luczak.tcp.weatherapp.controller;

import com.luczak.tcp.weatherapp.exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrorAdviceController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ErrorAdviceController.class);

    @ExceptionHandler(value = ClientException.class)
    public ResponseEntity<String> handleOpenWeatherClientException(ClientException exception) {

        LOGGER.warn("Error while call to Rest client: ", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with Weather Client");
    }
}
