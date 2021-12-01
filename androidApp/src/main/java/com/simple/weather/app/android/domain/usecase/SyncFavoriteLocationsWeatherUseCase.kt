package com.simple.weather.app.android.domain.usecase

import com.simple.weather.app.android.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.android.domain.repository.WeatherRepository
import com.simple.weather.app.android.utils.isSameDay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.util.Calendar

interface ISyncFavoriteLocationsWeatherUseCase {
    suspend operator fun invoke()
}

internal class SyncFavoriteLocationsWeatherUseCase(
    private val favoriteLocationsRepository: FavoriteLocationsRepository,
    private val weatherRepository: WeatherRepository,
    private val updateFavoriteLocationWeatherUseCase: IUpdateFavoriteLocationWeatherUseCase
) : ISyncFavoriteLocationsWeatherUseCase {

    override suspend fun invoke() {
        val now = Calendar.getInstance()
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