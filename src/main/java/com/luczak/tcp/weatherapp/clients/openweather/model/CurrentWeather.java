package com.luczak.tcp.weatherapp.clients.openweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.luczak.tcp.weatherapp.utils.UnixToLocalDateDeserializer;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeather {
    @JsonDeserialize(using = UnixToLocalDateDeserializer.class)
    @JsonProperty("dt")
    private LocalDate date;
    @NotNull
    private Double temp;
    @NotNull
    private Integer humidity;

}
