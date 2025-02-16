package com.example.weather.service;

import com.example.weather.model.Weather;
import com.example.weather.model.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Сервис для работы с погодными данными.
 * Этот класс предоставляет методы для получения текущей погоды и прогноза погоды для
 * заданного города, используя API OpenWeatherMap. Он также поддерживает логирование
 * запросов и ответов для улучшения отладки и мониторинга.
 */
@Service
public class WeatherService {

    @Value("${weather.url}")
    private String weatherUrl;

    @Value(("${forecast.url}"))
    private String dailyForecastUrl;
    @Value("${weather.api-key}")
    private String weatherApiKey;

    Logger logger = LoggerFactory.getLogger(Weather.class);
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Weather getWeatherByCity(String city) {
        logger.debug("Requesting weather for city: {} ", city);
        Weather weather = restTemplate.exchange(
                        weatherUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(HttpHeaders.EMPTY),
                        Weather.class,
                        city,
                        weatherApiKey)
                .getBody();
        logger.debug("The weather for {} is {}", city, weather);
        return weather;
    }

    public WeatherForecast getDailyForecastWeather(String city) {
        logger.debug("Requesting weekly forecast for city: {}", city);
        WeatherForecast forecastWeather = restTemplate.exchange(
                        dailyForecastUrl,
                        HttpMethod.GET,
                        new HttpEntity<>(HttpHeaders.EMPTY),
                        WeatherForecast.class,
                        city,
                        weatherApiKey)
                .getBody();
        logger.debug("Weekly forecast for {} : {}", city, forecastWeather);
        return forecastWeather;
    }
}
