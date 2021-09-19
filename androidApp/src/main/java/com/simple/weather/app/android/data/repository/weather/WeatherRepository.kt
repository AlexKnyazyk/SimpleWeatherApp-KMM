package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.model.WeatherData

interface WeatherRepository {

    suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherData>

    suspend fun getCurrentWeather(ipv6: String): Result<WeatherData>

}