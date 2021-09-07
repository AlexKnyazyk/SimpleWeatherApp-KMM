package com.simple.weather.app.android.di

import com.simple.weather.app.android.data.repository.weather.WeatherRepository
import com.simple.weather.app.android.data.repository.weather.WeatherRepositoryImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val repositoriesModule: DI.Module
    get() = DI.Module("repositoriesModule") {
        bindSingleton<WeatherRepository> { WeatherRepositoryImpl(instance()) }
    }