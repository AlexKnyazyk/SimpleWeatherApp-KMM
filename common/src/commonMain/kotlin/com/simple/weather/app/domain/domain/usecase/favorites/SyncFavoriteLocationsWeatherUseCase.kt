package com.simple.weather.app.domain.domain.usecase.favorites

import com.simple.weather.app.domain.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.domain.domain.repository.WeatherRepository
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
//        val now = Calendar.getInstance()
        favoriteLocationsRepository.allFavoriteLocations()
            .map { list ->
//                list.filter { it.tempC == null || it.updateTimestamp?.isSameDay(now) != true } TODO
                list
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