package com.luczak.tcp.weatherapp.clients.openweather.converter;

import com.luczak.tcp.weatherapp.clients.ForecastConverter;
import com.luczak.tcp.weatherapp.clients.openweather.model.CurrentWeather;
import com.luczak.tcp.weatherapp.clients.openweather.model.DailyTemperature;
import com.luczak.tcp.weatherapp.clients.openweather.model.DailyWeather;
import com.luczak.tcp.weatherapp.clients.openweather.model.OpenWeatherResponse;
import com.luczak.tcp.weatherapp.model.ForecastDay;
import com.luczak.tcp.weatherapp.model.ForecastResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class OpenWeatherConverter implements ForecastConverter<OpenWeatherResponse> {

    @Override
    public ForecastResponse convertToResponse(OpenWeatherResponse clientApiResponse) {

        CurrentWeather currentWeather = clientApiResponse.getCurrent();
        var forecastResponse = new ForecastResponse();
        forecastResponse.setCurrentTemp(currentWeather.getTemp());
        forecastResponse.setCurrentHumidity(currentWeather.getHumidity());
        List<ForecastDay> forecastDays = getForecastDays(clientApiResponse.getDaily());
        forecastResponse.setForecastDays(forecastDays);
        return forecastResponse;
    }

    private List<ForecastDay> getForecastDays(List<DailyWeather> daily) {

        return daily
                .stream()
                .map(OWDailyToForecastDay())
                .collect(Collectors.toList());

    }

    private Function<DailyWeather, ForecastDay> OWDailyToForecastDay() {

        return dailyWeather -> {
            var forecastDay = new ForecastDay();
            forecastDay.setDay(dailyWeather.getDate());
            DailyTemperature dailyTemperature = dailyWeather.getDailyTemperature();
            forecastDay.setTempDay(dailyTemperature.getDay());
            forecastDay.setTempNight(dailyTemperature.getNight());
            return forecastDay;
        };
    }

}
