package com.luczak.tcp.weatherapp.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class UnixToLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        String str = jsonParser.getText();
        Instant instant = Instant.ofEpochSecond(Long.parseLong(str));
        return LocalDate.ofInstant(instant, ZoneOffset.UTC);
    }

}
