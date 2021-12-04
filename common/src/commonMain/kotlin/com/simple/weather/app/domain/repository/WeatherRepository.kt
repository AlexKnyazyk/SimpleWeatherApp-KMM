package com.simple.weather.app.domain.repository

import com.simple.weather.app.domain.model.SearchLocationModel
import com.simple.weather.app.domain.model.WeatherModel

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherModel>

    suspend fun getCurrentWeather(locationName: String): Result<WeatherModel>

    suspend fun getCurrentWeatherByAutoIp(): Result<WeatherModel>

    suspend fun searchLocation(query: String): Result<List<SearchLocationModel>>

}