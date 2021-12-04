package com.simple.weather.app.domain.repository

import com.simple.weather.app.data.model.CResult
import com.simple.weather.app.domain.model.SearchLocationModel
import com.simple.weather.app.domain.model.WeatherModel

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): CResult<WeatherModel>

    suspend fun getCurrentWeather(locationName: String): CResult<WeatherModel>

    suspend fun getCurrentWeatherByAutoIp(): CResult<WeatherModel>

    suspend fun searchLocation(query: String): CResult<List<SearchLocationModel>>

}