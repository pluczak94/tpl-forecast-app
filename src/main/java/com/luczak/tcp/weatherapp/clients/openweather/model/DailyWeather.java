package com.luczak.tcp.weatherapp.clients.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.luczak.tcp.weatherapp.utils.UnixToLocalDateDeserializer;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyWeather {

    @JsonDeserialize(using = UnixToLocalDateDeserializer.class)
    @JsonProperty("dt")
    private LocalDate date;
    @JsonProperty("temp")
    private DailyTemperature dailyTemperature;
}
