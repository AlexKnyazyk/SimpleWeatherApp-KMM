package com.simple.weather.app.android.di

import com.simple.weather.app.android.domain.usecase.GetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.IGetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.ISearchLocationUseCase
import com.simple.weather.app.android.domain.usecase.SearchLocationUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val domainModule : DI.Module
    get() = DI.Module("domainModule") {
        bindProvider<IGetWeatherUseCase> { GetWeatherUseCase(instance()) }
        bindProvider<ISearchLocationUseCase> { SearchLocationUseCase(instance()) }
    }