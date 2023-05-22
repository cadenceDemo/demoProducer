package com.crux.cadence.demo.producer.service;

import com.crux.cadence.demo.producer.entity.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;

@Service
public class WeatherService implements WeatherUriHelper {
    @NonNull
    private String weatherBaseUrl;
    @NonNull
    private ObjectMapper objectMapper;

    public WeatherService(@Value("${app.weather.url}")@NonNull String weatherBaseUrl, @NonNull ObjectMapper objectMapper) {
        this.weatherBaseUrl = weatherBaseUrl;
        this.objectMapper = objectMapper;
    }

    public WeatherResponse getWeather(Double latitude, Double longitude) throws MalformedURLException {
        try {
            URI uri = getWeatherUri(latitude, longitude, weatherBaseUrl);
            WeatherResponse res = objectMapper.readValue(uri.toURL(), WeatherResponse.class);
            return res;
        } catch (Exception e) {
            System.out.println("eror getWeather "+e.getMessage());
            throw new MalformedURLException("Could not get weather");
        }
    }
}
