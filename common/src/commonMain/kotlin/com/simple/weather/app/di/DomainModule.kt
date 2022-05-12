package com.simple.weather.app.di

import com.simple.weather.app.domain.usecase.favorites.ISyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.domain.usecase.favorites.IUpdateFavoriteLocationWeatherUseCase
import com.simple.weather.app.domain.usecase.favorites.SyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.domain.usecase.favorites.UpdateFavoriteLocationWeatherUseCase
import com.simple.weather.app.domain.usecase.search.AddSearchLocationToFavoritesUseCase
import com.simple.weather.app.domain.usecase.search.IAddSearchLocationToFavoritesUseCase
import com.simple.weather.app.domain.usecase.search.ISearchLocationUseCase
import com.simple.weather.app.domain.usecase.search.SearchLocationUseCase
import com.simple.weather.app.domain.usecase.weather.GetWeatherUseCase
import com.simple.weather.app.domain.usecase.weather.IGetWeatherUseCase
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.factoryOf

internal val domainModule = module {
    factoryOf(::GetWeatherUseCase) bind IGetWeatherUseCase::class
    factoryOf(::SearchLocationUseCase) bind ISearchLocationUseCase::class
    factoryOf(::SyncFavoriteLocationsWeatherUseCase) bind ISyncFavoriteLocationsWeatherUseCase::class
    factoryOf(::UpdateFavoriteLocationWeatherUseCase) bind IUpdateFavoriteLocationWeatherUseCase::class
    factoryOf(::AddSearchLocationToFavoritesUseCase) bind IAddSearchLocationToFavoritesUseCase::class
}