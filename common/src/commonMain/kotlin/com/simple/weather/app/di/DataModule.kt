package com.simple.weather.app.di

import com.simple.weather.app.data.datasource.local.FavoriteLocationsLocalDataSource
import com.simple.weather.app.data.datasource.local.FavoriteLocationsLocalDataSourceImpl
import com.simple.weather.app.data.datasource.local.SettingsLocalDataSource
import com.simple.weather.app.data.datasource.local.SettingsLocalDataSourceImpl
import com.simple.weather.app.data.datasource.remote.WeatherRemoteDataSource
import com.simple.weather.app.data.datasource.remote.WeatherRemoteDataSourceImpl
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.singleOf

internal val dataModule = module {
    singleOf(::WeatherRemoteDataSourceImpl) bind WeatherRemoteDataSource::class
    singleOf(::FavoriteLocationsLocalDataSourceImpl) bind FavoriteLocationsLocalDataSource::class
    singleOf(::SettingsLocalDataSourceImpl) bind SettingsLocalDataSource::class
}