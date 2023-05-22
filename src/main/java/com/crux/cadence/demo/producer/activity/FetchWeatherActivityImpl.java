package com.crux.cadence.demo.producer.activity;

import com.crux.cadence.demo.producer.entity.CurrentWeather;
import com.crux.cadence.demo.producer.entity.WeatherResponse;
import com.crux.cadence.demo.producer.mapper.CurrentWeatherMapper;
import com.crux.cadence.demo.producer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FetchWeatherActivityImpl implements FetchWeatherActivity {
    @NonNull
    private final WeatherService weatherService;
    @NonNull
    private final CurrentWeatherMapper currentWeatherMapper;

    @Override
    public CurrentWeather getWeather(Double latitude, Double longitude) {
        try {
            WeatherResponse resp = weatherService.getWeather(latitude, longitude);
            return currentWeatherMapper.toCurrentWeather(resp);
        } catch (Exception ex) {
            System.out.println("error "+ex.getMessage());
            return null;
        }
    }
}
