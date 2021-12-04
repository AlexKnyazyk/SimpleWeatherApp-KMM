package com.simple.weather.app.data.datasource.remote

import com.simple.weather.app.data.model.CResult
import com.simple.weather.app.data.model.response.SearchLocation
import com.simple.weather.app.data.model.response.WeatherData

internal interface WeatherRemoteDataSource {

    suspend fun getCurrentWeather(lat: Double, lon: Double): CResult<WeatherData>

    suspend fun getCurrentWeather(locationName: String): CResult<WeatherData>

    suspend fun getCurrentWeatherByAutoIp(): CResult<WeatherData>

    suspend fun searchLocation(query: String): CResult<List<SearchLocation>>

}