package com.simple.weather.app.di

import com.simple.weather.app.data.repository.location.DeviceLocationRepositoryImpl
import com.simple.weather.app.data.repository.location.FavoriteLocationsRepositoryImpl
import com.simple.weather.app.data.repository.settings.SettingsRepositoryImpl
import com.simple.weather.app.data.repository.weather.WeatherRepositoryImpl
import com.simple.weather.app.domain.repository.DeviceLocationRepository
import com.simple.weather.app.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.domain.repository.SettingsRepository
import com.simple.weather.app.domain.repository.WeatherRepository
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

internal val repositoriesModule = module {
    singleOf(::WeatherRepositoryImpl) bind WeatherRepository::class
    singleOf(::DeviceLocationRepositoryImpl) bind DeviceLocationRepository::class
    singleOf(::FavoriteLocationsRepositoryImpl) bind FavoriteLocationsRepository::class
    singleOf(::SettingsRepositoryImpl) bind SettingsRepository::class
}