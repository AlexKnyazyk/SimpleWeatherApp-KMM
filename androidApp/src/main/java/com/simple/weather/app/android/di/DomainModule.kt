package com.simple.weather.app.android.di

import com.simple.weather.app.android.domain.usecase.GetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.IGetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.ISearchLocationUseCase
import com.simple.weather.app.android.domain.usecase.SearchLocationUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<IGetWeatherUseCase> { GetWeatherUseCase(get()) }
    factory<ISearchLocationUseCase> { SearchLocationUseCase(get()) }
}