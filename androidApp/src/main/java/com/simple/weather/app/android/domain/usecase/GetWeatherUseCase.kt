package com.simple.weather.app.android.domain.usecase

import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.domain.repository.WeatherRepository

interface IGetWeatherUseCase {
    suspend operator fun invoke(request: WeatherRequest): Result<WeatherModel>
}

internal class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) : IGetWeatherUseCase {

    override suspend operator fun invoke(request: WeatherRequest): Result<WeatherModel> {
        return when (request) {
            is WeatherRequest.Location -> {
                weatherRepository.getCurrentWeather(request.lat, request.lon)
            }
            is WeatherRequest.Name -> {
                weatherRepository.getCurrentWeather(request.name)
            }
            is WeatherRequest.AutoIPAddress -> {
                weatherRepository.getCurrentWeatherByAutoIp()
            }
        }
    }
}