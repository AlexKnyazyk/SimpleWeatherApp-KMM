package com.simple.weather.app.di

import com.simple.weather.app.domain.domain.repository.DeviceLocationRepository
import com.simple.weather.app.domain.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.domain.domain.repository.SettingsRepository
import com.simple.weather.app.domain.domain.repository.WeatherRepository
import com.simple.weather.app.data.repository.location.DeviceLocationRepositoryImpl
import com.simple.weather.app.data.repository.location.FavoriteLocationsRepositoryImpl
import com.simple.weather.app.data.repository.settings.SettingsRepositoryImpl
import com.simple.weather.app.data.repository.weather.WeatherRepositoryImpl
import org.koin.dsl.module

internal val repositoriesModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<DeviceLocationRepository> { DeviceLocationRepositoryImpl() }
    single<FavoriteLocationsRepository> { FavoriteLocationsRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}