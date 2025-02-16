package com.example.weather.model;

import java.math.BigDecimal;

public class WeatherWind {

    private BigDecimal speed;
    private Integer deg;

    public WeatherWind() {
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public Integer getDeg() {
        return deg;
    }
}
