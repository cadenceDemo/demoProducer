package com.crux.cadence.demo.producer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class WeatherResponse {
    public double latitude;
    public double longitude;
    public double generationtime_ms;
    public double utc_offset_seconds;
    public String timezone;
    public String timezone_abbreviation;
    public double elevation;
    public WeatherResponseData current_weather;
}
