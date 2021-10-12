package com.simple.weather.app.android.di

import com.simple.weather.app.android.domain.mapper.SearchLocationMapper
import com.simple.weather.app.android.domain.mapper.WeatherMapper
import com.simple.weather.app.android.domain.usecase.GetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.SearchLocationUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val domainModule : DI.Module
    get() = DI.Module("domainModule") {
        bindProvider { WeatherMapper() }
        bindProvider { SearchLocationMapper() }

        bindProvider { GetWeatherUseCase(instance(), instance()) }
        bindProvider { SearchLocationUseCase(instance(), instance()) }
    }