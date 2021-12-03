package com.simple.weather.app.domain.domain.usecase.favorites

import com.simple.weather.app.domain.domain.model.FavoriteLocationModel
import com.simple.weather.app.domain.domain.model.WeatherModel
import com.simple.weather.app.domain.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.utils.dateTimestampNow

interface IUpdateFavoriteLocationWeatherUseCase {
    suspend operator fun invoke(favorite: FavoriteLocationModel, weather: WeatherModel)
}

internal class UpdateFavoriteLocationWeatherUseCase(
    private val favoriteLocationsRepository: FavoriteLocationsRepository,
) : IUpdateFavoriteLocationWeatherUseCase {

    override suspend fun invoke(favorite: FavoriteLocationModel, weather: WeatherModel) {
        favoriteLocationsRepository.addOrUpdate(
            favorite.copy(
                tempC = weather.tempC,
                tempF = weather.tempF,
                weatherConditionIconUrl = weather.weatherConditionIconUrl,
                updateTimestamp = dateTimestampNow()
            )
        )
    }
}