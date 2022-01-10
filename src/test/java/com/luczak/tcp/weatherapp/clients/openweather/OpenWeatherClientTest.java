package com.luczak.tcp.weatherapp.clients.openweather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luczak.tcp.weatherapp.clients.openweather.validator.OpenWeatherResponseValidator;
import com.luczak.tcp.weatherapp.config.OpenWeatherConfig;
import com.luczak.tcp.weatherapp.exception.ClientConnectionException;
import com.luczak.tcp.weatherapp.exception.ClientResponseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class OpenWeatherClientTest {

    private ObjectMapper objectMapper = new ObjectMapper();
    private MockRestServiceServer mockServer;


    @Spy
    private RestTemplate restTemplate = new RestTemplate();
    @Spy
    private OpenWeatherConfig config = new OpenWeatherConfig();
    @Spy
    private OpenWeatherResponseValidator validator = new OpenWeatherResponseValidator();

    @InjectMocks
    private OpenWeatherClient openWeatherClient;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockServer = MockRestServiceServer.createServer(restTemplate);
        config.setApiKey("API_KEY");
        config.setUrl("http://localhost:8081/api");
        config.setUnits("metric");
        config.setExclude("alerts");
    }

    @Test
    void nullResponseShouldThrownException() throws URISyntaxException, JsonProcessingException {

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:8081/api?lat=1.0&lon=1.0&units=metric&exclude=alerts&appId=API_KEY")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(null))
                );

        Exception exception = assertThrows(ClientResponseException.class, () -> openWeatherClient.getClientApiResponse(1, 1));
        assertEquals("Invalid response from OpenWeather Api", exception.getMessage());
    }

    @Test
    void wrongResponseShouldThrownException() throws URISyntaxException, JsonProcessingException {

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:8081/api?lat=1.0&lon=1.0&units=metric&exclude=alerts&appId=API_KEY")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(objectMapper.writeValueAsString(5))
                );

        Exception exception = assertThrows(ClientConnectionException.class, () -> openWeatherClient.getClientApiResponse(1, 1));
        assertEquals(RestClientException.class, exception.getCause().getClass());
    }

    @Test
    void InternalServerErrorShouldThrownException() throws URISyntaxException {

        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI("http://localhost:8081/api?lat=1.0&lon=1.0&units=metric&exclude=alerts&appId=API_KEY")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                );

        Exception exception = assertThrows(ClientConnectionException.class, () -> openWeatherClient.getClientApiResponse(1, 1));
        assertEquals(HttpServerErrorException.InternalServerError.class, exception.getCause().getClass());
    }

}