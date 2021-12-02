package com.simple.weather.app.android.di

import com.simple.weather.app.android.domain.usecase.weather.GetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.weather.IGetWeatherUseCase
import com.simple.weather.app.android.domain.usecase.search.ISearchLocationUseCase
import com.simple.weather.app.android.domain.usecase.favorites.ISyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.android.domain.usecase.favorites.IUpdateFavoriteLocationWeatherUseCase
import com.simple.weather.app.android.domain.usecase.search.SearchLocationUseCase
import com.simple.weather.app.android.domain.usecase.favorites.SyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.android.domain.usecase.favorites.UpdateFavoriteLocationWeatherUseCase
import com.simple.weather.app.android.domain.usecase.search.AddSearchLocationToFavoritesUseCase
import com.simple.weather.app.android.domain.usecase.search.IAddSearchLocationToFavoritesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<IGetWeatherUseCase> { GetWeatherUseCase(get(), get(), get()) }
    factory<ISearchLocationUseCase> { SearchLocationUseCase(get()) }
    factory<ISyncFavoriteLocationsWeatherUseCase> { SyncFavoriteLocationsWeatherUseCase(get(), get(), get()) }
    factory<IUpdateFavoriteLocationWeatherUseCase> { UpdateFavoriteLocationWeatherUseCase(get()) }
    factory<IAddSearchLocationToFavoritesUseCase> { AddSearchLocationToFavoritesUseCase(get()) }
}