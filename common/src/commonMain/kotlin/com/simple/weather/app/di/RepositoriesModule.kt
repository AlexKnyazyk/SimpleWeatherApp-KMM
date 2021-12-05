package com.simple.weather.app.di

import com.simple.weather.app.data.repository.location.FavoriteLocationsRepositoryImpl
import com.simple.weather.app.data.repository.settings.SettingsRepositoryImpl
import com.simple.weather.app.data.repository.weather.WeatherRepositoryImpl
import com.simple.weather.app.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.domain.repository.SettingsRepository
import com.simple.weather.app.domain.repository.WeatherRepository
import org.koin.dsl.module

internal val repositoriesModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<FavoriteLocationsRepository> { FavoriteLocationsRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}