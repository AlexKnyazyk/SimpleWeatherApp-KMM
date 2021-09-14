package com.simple.weather.app.android.domain.model

data class ForecastItem(
    val date: Long,
    val mode: ForecastItemMode,
    val temperature: Double,
    val temperatureMax: Double = 0.0,
    val temperatureMin: Double = 0.0,
    val iconUrl: String,
    val windSpeed: Double,
    val windDir: String
)