package com.luczak.tcp.weatherapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import utils.ResourceReader;

import java.net.URI;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ForecastApiTest {

    public static final String MOCK_API = "http://localhost:8081/api?lat=%d.0&lon=%d.0&units=metric&exclude=alerts,minutely,hourly&appId=API_KEY";
    public static final String REQUEST_API = "/api/currentForecast?latitude=%d&longitude=%d";
    @Autowired
    private MockMvc mvc;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:/app.json")
    private Resource correctOWAPIResponse;

    private MockRestServiceServer mockServer;


    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }


    @Test
    void nullResponseFromOWApi() throws Exception {

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(String.format(MOCK_API, 1, 1))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(null))
                );

        mvc.perform(get(String.format(REQUEST_API, 1, 1)))
                .andDo(print()).andExpect(status().is5xxServerError());
    }

    @Test
    void wrongResponseFromOWApi() throws Exception {
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(String.format(MOCK_API, 2, 2))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"key\":\"value\"}")
                );

        mvc.perform(get(String.format(REQUEST_API, 2, 2)))
                .andDo(print()).andExpect(status().is5xxServerError());
    }

    @Test
    void correctResponseFromOWApi() throws Exception {
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(String.format(MOCK_API, 3, 3))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(ResourceReader.asString(correctOWAPIResponse))
                );

        mvc.perform(get(String.format(REQUEST_API, 3, 3)))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void exceptionFromOWApi() throws Exception {
        mockServer.expect(ExpectedCount.times(3),
                requestTo(new URI(String.format(MOCK_API, 4, 4))))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR));

        mvc.perform(get(String.format(REQUEST_API, 4, 4)))
                .andDo(print()).andExpect(status().is5xxServerError());
    }


}