package com.crux.cadence.demo.producer.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Getter
@Jacksonized
public class WeatherResponseData {
    private double temperature;
    private double windspeed;
    private double winddirection;
    private double weathercode;
    private boolean is_day;
    private String time;
}
