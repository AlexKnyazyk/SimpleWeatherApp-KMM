package com.simple.weather.app.android.di

import com.simple.weather.app.android.domain.repository.LocationRepository
import com.simple.weather.app.android.data.repository.location.LocationRepositoryImpl
import com.simple.weather.app.android.domain.repository.WeatherRepository
import com.simple.weather.app.android.data.repository.weather.WeatherRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get()) }
    single<LocationRepository> { LocationRepositoryImpl(get()) }
}