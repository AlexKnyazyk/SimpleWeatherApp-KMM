package com.simple.weather.app.android.domain.usecase

import com.simple.weather.app.android.data.repository.weather.WeatherRepository
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.domain.mapper.WeatherMapper

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val weatherMapper: WeatherMapper,
) {

    suspend operator fun invoke(request: WeatherRequest) : Result<WeatherModel> {
        return when(request) {
            is WeatherRequest.Location -> {
                weatherRepository.getCurrentWeather(request.lat, request.lon)
            }
            is WeatherRequest.IPAddress -> {
                weatherRepository.getCurrentWeather(request.ipv6)
            }
        }.map(weatherMapper::map)
    }
}