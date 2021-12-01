package com.simple.weather.app.android.di

import com.simple.weather.app.android.data.datasource.local.FavoriteLocationsLocalDataSource
import com.simple.weather.app.android.data.datasource.local.FavoriteLocationsLocalDataSourceImpl
import com.simple.weather.app.android.data.datasource.remote.WeatherRemoteDataSource
import com.simple.weather.app.android.data.datasource.remote.WeatherRemoteDataSourceImpl
import org.koin.dsl.module

val dataModule = module {
    single<WeatherRemoteDataSource> { WeatherRemoteDataSourceImpl(get()) }
    single<FavoriteLocationsLocalDataSource> { FavoriteLocationsLocalDataSourceImpl(get()) }
}