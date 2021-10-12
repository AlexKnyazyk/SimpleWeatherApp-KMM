package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.model.response.SearchLocation
import com.simple.weather.app.android.data.model.response.WeatherData

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherData>

    suspend fun getCurrentWeather(ipv6: String): Result<WeatherData>

    suspend fun searchLocation(query: String): Result<List<SearchLocation>>

}