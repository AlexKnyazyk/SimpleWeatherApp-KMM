package com.simple.weather.app.android.di

import com.simple.weather.app.android.data.repository.location.DeviceLocationRepositoryImpl
import com.simple.weather.app.android.data.repository.location.FavoriteLocationsRepositoryImpl
import com.simple.weather.app.android.data.repository.weather.WeatherRepositoryImpl
import com.simple.weather.app.android.domain.repository.DeviceLocationRepository
import com.simple.weather.app.android.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.android.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<DeviceLocationRepository> { DeviceLocationRepositoryImpl(get()) }
    single<FavoriteLocationsRepository> { FavoriteLocationsRepositoryImpl(get()) }
}