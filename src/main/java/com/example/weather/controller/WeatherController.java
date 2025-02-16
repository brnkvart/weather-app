package com.example.weather.controller;

import com.example.weather.model.Weather;
import com.example.weather.model.WeatherForecast;
import com.example.weather.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller для работы с данными о погоде.
 * Предоставляет REST API для получения данных о погоде.
 */
@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Получает данные о погоде для указанного города.
     *
     * @param city Название города, для которого требуется получить данные о погоде.
     * @return ResponseEntity с данными о погоде и статусом 200 OK, если данные успешно получены.
     */
    @Operation(
            summary = "Получение погоды по выбору города",
            tags = "Погода"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Прогноз успешно получен"),
                    @ApiResponse(responseCode = "404", description = "Невалидный параметр города")

            }
    )
    @GetMapping("/city")
    public ResponseEntity<Weather> getWeatherByCity(@RequestParam("city") String city) {
        Weather weather = weatherService.getWeatherByCity(city);
        return ResponseEntity.ok(weather);
    }

    /**
     * Получает недельный прогноз погоды для заданного города.
     *
     * @param city Название города, для которого необходимо получить прогноз погоды.
     * @return ResponseEntity содержащий объект WeatherForecast с прогнозом погоды
     * или 404, если информация о погоде не найдена.
     */
    @Operation(summary = "Получить ежедневный прогноз погоды",
            tags = "Погода"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Прогноз успешно получен"),
                    @ApiResponse(responseCode = "404", description = "Невалидный параметр города")

            }
    )
    @GetMapping("/forecast")
    public ResponseEntity<WeatherForecast> getDailyForecastWeather(@RequestParam(name = "city") String city) {
        WeatherForecast forecastWeather = weatherService.getDailyForecastWeather(city);
        return ResponseEntity.ok(forecastWeather);
    }
}
