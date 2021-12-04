package com.simple.weather.app.di

import com.simple.weather.app.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.domain.repository.SettingsRepository
import com.simple.weather.app.domain.repository.WeatherRepository
import com.simple.weather.app.domain.usecase.favorites.ISyncFavoriteLocationsWeatherUseCase
import com.simple.weather.app.domain.usecase.favorites.IUpdateFavoriteLocationWeatherUseCase
import com.simple.weather.app.domain.usecase.search.IAddSearchLocationToFavoritesUseCase
import com.simple.weather.app.domain.usecase.search.ISearchLocationUseCase
import com.simple.weather.app.domain.usecase.weather.IGetWeatherUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

object DiContainer {

    private val component: KoinComponent by lazy { object : KoinComponent {} }

    fun init() {
        startKoin {
            modules(
                commonExpectActualModule,
                databaseModule,
                networkModule,
                dataModule,
                repositoriesModule,
                domainModule
            )
        }
    }

    fun getWeatherUseCase(): IGetWeatherUseCase = component.get()
    fun searchLocationUseCase(): ISearchLocationUseCase = component.get()
    fun syncFavoriteLocationsWeatherUseCase(): ISyncFavoriteLocationsWeatherUseCase = component.get()
    fun updateFavoriteLocationWeatherUseCase(): IUpdateFavoriteLocationWeatherUseCase = component.get()
    fun addSearchLocationToFavoritesUseCase(): IAddSearchLocationToFavoritesUseCase = component.get()

    fun getWeatherRepository(): WeatherRepository = component.get()
    fun getFavoriteLocationsRepository(): FavoriteLocationsRepository = component.get()
    fun getSettingsRepository(): SettingsRepository = component.get()

}