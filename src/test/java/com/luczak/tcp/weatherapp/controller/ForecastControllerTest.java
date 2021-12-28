package com.luczak.tcp.weatherapp.controller;

import com.luczak.tcp.weatherapp.service.ForecastService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ForecastController.class)
class ForecastControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ForecastService service;


    @Test
    void noParametersProvided_shouldReturnBadRequest() throws Exception {

        mvc.perform(get("/api/forecast")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void correctRequest_shouldInvokeForecastService() throws Exception {

        mvc.perform(get("/api/forecast?latitude=1&longitude=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(service, Mockito.times(1)).getForecastForLocation(1, 1);
    }
}
