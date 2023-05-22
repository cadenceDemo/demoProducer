package com.crux.cadence.demo.producer.service;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public interface WeatherUriHelper {
    default URI getWeatherUri(Double latitude, Double longitude, String baseUrl)
            throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl);
        uriBuilder.addParameter("latitude", latitude.toString());
        uriBuilder.addParameter("longitude", longitude.toString());
        uriBuilder.addParameter("current_weather", "true");
        return uriBuilder.build();
    }
}
