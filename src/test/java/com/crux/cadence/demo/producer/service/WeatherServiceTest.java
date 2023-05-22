package com.crux.cadence.demo.producer.service;

import com.crux.cadence.demo.producer.entity.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    private static final String host = "weather.service";

    private static final String baseUrl = "http://" + host;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private WeatherResponse weatherResponse;

    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        weatherService = new WeatherService(baseUrl, objectMapper);
    }

    @Test
    public void testInvokesExternalService() throws IOException {
        when(objectMapper.readValue(Mockito.any(URL.class), eq(WeatherResponse.class))).thenReturn(weatherResponse);

        var result = weatherService.getWeather(55.0, 43.1);

        var urlCaptor = ArgumentCaptor.forClass(URL.class);
        verify(objectMapper).readValue(urlCaptor.capture(), eq(WeatherResponse.class));

        var url = urlCaptor.getValue();

        assertThat(url.getHost(), equalTo(host));
        assertThat(url.getQuery(), containsString("latitude=55.0"));
        assertThat(url.getQuery(), containsString("longitude=43.1"));
        assertThat(url.getQuery(), containsString("current_weather=true"));

        assertThat(result, sameInstance(weatherResponse));
    }

}