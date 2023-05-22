package com.crux.cadence.demo.producer.mapper;

import com.crux.cadence.demo.producer.entity.CurrentWeather;
import com.crux.cadence.demo.producer.entity.WeatherResponse;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CurrentWeatherMapper {

    @Mapping(source = "current_weather.temperature", target = "temperature")
    @Mapping(source = "current_weather.windspeed", target = "windspeed")
    @Mapping(source = "current_weather.winddirection", target = "winddirection")
    CurrentWeather toCurrentWeather(WeatherResponse apiWeather);
}
