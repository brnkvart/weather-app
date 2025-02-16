package com.example.weather.model;

public class Weather {

    private WeatherMain main;
    private WeatherWind wind;

    private String dt_txt;
    private String name;

    public Weather() {
    }

    public WeatherMain getMain() {
        return main;
    }

    public WeatherWind getWind() {
        return wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public String getName() {
        return name;
    }
}
