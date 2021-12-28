package com.luczak.tcp.weatherapp.clients.openweather.converter;


import com.luczak.tcp.weatherapp.clients.openweather.model.CurrentWeather;
import com.luczak.tcp.weatherapp.clients.openweather.model.DailyTemperature;
import com.luczak.tcp.weatherapp.clients.openweather.model.DailyWeather;
import com.luczak.tcp.weatherapp.clients.openweather.model.OpenWeatherResponse;
import com.luczak.tcp.weatherapp.model.ForecastDay;
import com.luczak.tcp.weatherapp.model.ForecastResponse;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenWeatherConverterTest {

    private OpenWeatherConverter openWeatherConverter = new OpenWeatherConverter();

    @Test
    void test() {
        ForecastResponse forecastResponse = openWeatherConverter.convertToResponse(getOpenWeatherResponse());
        ForecastDay forecastDay = forecastResponse.getForecastDays().get(0);
        assertEquals(1.0, forecastResponse.getCurrentTemp());
        assertEquals(0, forecastResponse.getCurrentHumidity());
        assertEquals(1.0, forecastDay.getTempDay());
        assertEquals(1.0, forecastDay.getTempNight());
    }

    private OpenWeatherResponse getOpenWeatherResponse() {
        OpenWeatherResponse openWeatherResponse = new OpenWeatherResponse();
        CurrentWeather currentWeather = getCurrentWeather(1, 0);
        openWeatherResponse.setDaily(Collections.singletonList(getDailyWeather(1, 1)));
        openWeatherResponse.setCurrent(currentWeather);
        return openWeatherResponse;
    }

    private DailyWeather getDailyWeather(double dayTemp, double nightTemp) {
        DailyWeather dailyWeather = new DailyWeather();
        dailyWeather.setDailyTemperature(getDailyTemp(dayTemp, nightTemp));
        return dailyWeather;
    }

    private DailyTemperature getDailyTemp(double dayTemp, double nightTemp) {
        DailyTemperature dailyTemperature = new DailyTemperature();
        dailyTemperature.setDay(dayTemp);
        dailyTemperature.setNight(nightTemp);
        return dailyTemperature;
    }

    private CurrentWeather getCurrentWeather(double temp, int humidity) {
        CurrentWeather currentWeather = new CurrentWeather();
        currentWeather.setTemp(temp);
        currentWeather.setHumidity(humidity);
        return currentWeather;
    }

}