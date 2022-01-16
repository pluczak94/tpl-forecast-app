package com.luczak.tcp.weatherapp.validators;


import com.luczak.tcp.weatherapp.model.ClientApiResponse;

public interface ClientResponseValidator<T extends ClientApiResponse> {

    boolean isValid(T response);

}
