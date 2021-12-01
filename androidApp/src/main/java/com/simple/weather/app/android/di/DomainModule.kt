package com.simple.weather.app.android.di

import com.simple.weather.app.android.domain.usecase.GetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.IGetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.ISearchLocationUseCase
import com.simple.weather.app.android.domain.usecase.ISyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.android.domain.usecase.IUpdateFavoriteLocationWeatherUseCase
import com.simple.weather.app.android.domain.usecase.SearchLocationUseCase
import com.simple.weather.app.android.domain.usecase.SyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.android.domain.usecase.UpdateFavoriteLocationWeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<IGetWeatherUseCase> { GetWeatherUseCase(get()) }
    factory<ISearchLocationUseCase> { SearchLocationUseCase(get()) }
    factory<ISyncFavoriteLocationsWeatherUseCase> { SyncFavoriteLocationsWeatherUseCase(get(), get(), get()) }
    factory<IUpdateFavoriteLocationWeatherUseCase> { UpdateFavoriteLocationWeatherUseCase(get()) }
}