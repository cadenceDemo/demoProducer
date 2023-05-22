package com.crux.cadence.demo.producer.activity;

import com.crux.cadence.demo.producer.entity.CurrentWeather;
import com.crux.cadence.demo.producer.entity.WeatherResponse;
import com.crux.cadence.demo.producer.mapper.CurrentWeatherMapper;
import com.crux.cadence.demo.producer.service.WeatherService;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FetchWeatherActivityImplTest {

    @Mock
    private WeatherService weatherService;
    @Mock
    private CurrentWeatherMapper currentWeatherMapper;
    @Mock
    private WeatherResponse weatherResponse;
    @Mock
    private CurrentWeather currentWeather;

    private FetchWeatherActivityImpl fetchWeatherActivity;

    @BeforeEach
    public void setUp() {
        fetchWeatherActivity = new FetchWeatherActivityImpl(weatherService, currentWeatherMapper);
    }

    @Test
    public void fetchWeatherHappyPath() throws MalformedURLException {
        when(weatherService.getWeather(anyDouble(), anyDouble())).thenReturn(weatherResponse);
        when(currentWeatherMapper.toCurrentWeather(any(WeatherResponse.class))).thenReturn(currentWeather);

        var result = fetchWeatherActivity.getWeather(12.0, 230.0);

        verify(weatherService).getWeather(eq(12.0), eq(230.0));
        verify(currentWeatherMapper).toCurrentWeather(same(weatherResponse));

        assertThat(result, sameInstance(currentWeather));
    }

    @Test
    public void fetchWeatherFail() throws MalformedURLException {
        when(weatherService.getWeather(anyDouble(), anyDouble())).thenThrow(new MalformedURLException());

        var result = fetchWeatherActivity.getWeather(12.0, 230.0);

        verify(weatherService).getWeather(eq(12.0), eq(230.0));

        assertThat(result,  IsNull.nullValue());
    }

}