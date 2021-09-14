package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.model.WeatherData

interface WeatherRepository {

    suspend fun getCurrentWeather(): Result<WeatherData>
}