package com.simple.weather.app.android.domain.usecase.favorites

import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.domain.repository.FavoriteLocationsRepository
import java.util.*

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
                updateTimestamp = Calendar.getInstance()
            )
        )
    }
}