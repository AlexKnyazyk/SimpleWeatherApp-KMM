package com.simple.weather.app.di

import com.simple.weather.app.domain.domain.usecase.favorites.ISyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.domain.domain.usecase.favorites.IUpdateFavoriteLocationWeatherUseCase
import com.simple.weather.app.domain.domain.usecase.favorites.SyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.domain.domain.usecase.favorites.UpdateFavoriteLocationWeatherUseCase
import com.simple.weather.app.domain.domain.usecase.search.AddSearchLocationToFavoritesUseCase
import com.simple.weather.app.domain.domain.usecase.search.IAddSearchLocationToFavoritesUseCase
import com.simple.weather.app.domain.domain.usecase.search.ISearchLocationUseCase
import com.simple.weather.app.domain.domain.usecase.search.SearchLocationUseCase
import com.simple.weather.app.domain.domain.usecase.weather.GetWeatherUseCase
import com.simple.weather.app.domain.domain.usecase.weather.IGetWeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<IGetWeatherUseCase> { GetWeatherUseCase(get(), get(), get()) }
    factory<ISearchLocationUseCase> { SearchLocationUseCase(get()) }
    factory<ISyncFavoriteLocationsWeatherUseCase> { SyncFavoriteLocationsWeatherUseCase(get(), get(), get()) }
    factory<IUpdateFavoriteLocationWeatherUseCase> { UpdateFavoriteLocationWeatherUseCase(get()) }
    factory<IAddSearchLocationToFavoritesUseCase> { AddSearchLocationToFavoritesUseCase(get()) }
}