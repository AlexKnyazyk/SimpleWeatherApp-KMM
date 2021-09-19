package com.simple.weather.app.android.di

import com.simple.weather.app.android.domain.mapper.WeatherMapper
import com.simple.weather.app.android.domain.usecase.GetWeatherUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val domainModule : DI.Module
    get() = DI.Module("domainModule") {
        bindProvider { WeatherMapper() }

        bindProvider { GetWeatherUseCase(instance(), instance()) }
    }