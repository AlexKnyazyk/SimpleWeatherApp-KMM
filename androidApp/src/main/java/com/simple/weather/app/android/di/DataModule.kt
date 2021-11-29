package com.simple.weather.app.android.di

import com.simple.weather.app.android.data.datasource.remote.WeatherRemoteDataSource
import com.simple.weather.app.android.data.datasource.remote.WeatherRemoteDataSourceImpl
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val dataModule: DI.Module
    get() = DI.Module("dataModule") {
        bindSingleton<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(instance()) }
    }