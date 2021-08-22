package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.model.CurrentWeatherData

interface WeatherRepository {

    suspend fun getCurrentWeather(): Result<CurrentWeatherData>
}