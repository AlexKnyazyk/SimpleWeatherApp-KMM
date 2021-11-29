package com.simple.weather.app.android.domain.repository

import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.model.WeatherModel

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherModel>

    suspend fun getCurrentWeather(ipv6: String): Result<WeatherModel>

    suspend fun searchLocation(query: String): Result<List<SearchLocationModel>>

}