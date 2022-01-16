package com.luczak.tcp.weatherapp.clients.openweather.validator;

import com.luczak.tcp.weatherapp.clients.openweather.model.CurrentWeather;
import com.luczak.tcp.weatherapp.clients.openweather.model.OpenWeatherResponse;
import com.luczak.tcp.weatherapp.validators.ClientResponseValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Objects;

@Component
public class OpenWeatherResponseValidator implements ClientResponseValidator<OpenWeatherResponse> {

    @Override
    public boolean isValid(@Nullable OpenWeatherResponse response) {

        if (Objects.isNull(response)) {
            return false;
        }

        CurrentWeather current = response.getCurrent();
        if (Objects.isNull(current)) {
            return false;
        }
        if (Objects.isNull(current.getHumidity())) {
            return false;
        }
        return !Objects.isNull(current.getTemp());
    }
}
