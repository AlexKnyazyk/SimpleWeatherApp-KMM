package com.simple.weather.app.android.data.datasource.remote

import com.simple.weather.app.android.data.model.response.SearchLocation
import com.simple.weather.app.android.data.model.response.WeatherData

interface WeatherRemoteDataSource {

    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherData>

    suspend fun getCurrentWeatherByAutoIp(): Result<WeatherData>

    suspend fun searchLocation(query: String): Result<List<SearchLocation>>

}