package com.simple.weather.app.android.domain.usecase.weather

import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.domain.model.errors.FavoriteLocationNotFoundError
import com.simple.weather.app.android.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.android.domain.repository.WeatherRepository
import com.simple.weather.app.android.domain.usecase.favorites.IUpdateFavoriteLocationWeatherUseCase

interface IGetWeatherUseCase {
    suspend operator fun invoke(request: WeatherRequest): Result<WeatherModel>
}

internal class GetWeatherUseCase(
    private val favoriteLocationsRepository: FavoriteLocationsRepository,
    private val weatherRepository: WeatherRepository,
    private val updateFavoriteLocationWeatherUseCase: IUpdateFavoriteLocationWeatherUseCase
) : IGetWeatherUseCase {

    override suspend operator fun invoke(request: WeatherRequest): Result<WeatherModel> {
        return when (request) {
            is WeatherRequest.Location -> {
                weatherRepository.getCurrentWeather(request.lat, request.lon)
            }
            is WeatherRequest.FavoriteId -> {
                favoriteLocationsRepository.getById(request.id)?.let { favoriteModel ->
                    weatherRepository.getCurrentWeather(favoriteModel.name).onSuccess { weatherModel ->
                        updateFavoriteLocationWeatherUseCase(favoriteModel, weatherModel)
                    }
                } ?: Result.failure(FavoriteLocationNotFoundError())
            }
            is WeatherRequest.AutoIPAddress -> {
                weatherRepository.getCurrentWeatherByAutoIp()
            }
        }
    }
}