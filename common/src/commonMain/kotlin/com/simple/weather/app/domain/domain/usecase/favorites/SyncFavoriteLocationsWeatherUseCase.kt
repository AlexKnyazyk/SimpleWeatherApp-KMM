package com.simple.weather.app.domain.domain.usecase.favorites

import com.simple.weather.app.domain.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.domain.domain.repository.WeatherRepository
import com.simple.weather.app.utils.dateTimestampNow
import com.simple.weather.app.utils.isSameDay
import kotlinx.coroutines.flow.*

interface ISyncFavoriteLocationsWeatherUseCase {
    suspend operator fun invoke()
}

internal class SyncFavoriteLocationsWeatherUseCase(
    private val favoriteLocationsRepository: FavoriteLocationsRepository,
    private val weatherRepository: WeatherRepository,
    private val updateFavoriteLocationWeatherUseCase: IUpdateFavoriteLocationWeatherUseCase
) : ISyncFavoriteLocationsWeatherUseCase {

    override suspend fun invoke() {
        val now = dateTimestampNow()
        favoriteLocationsRepository.allFavoriteLocations()
            .map { list ->
                list.filter { it.tempC == null || it.updateTimestamp?.isSameDay(now) != true }
            }
            .flatMapConcat { it.asFlow() }
            .onEach { model ->
                weatherRepository.getCurrentWeather(model.name).onSuccess { weather ->
                    updateFavoriteLocationWeatherUseCase(model, weather)
                }
            }
            .collect()
    }
}