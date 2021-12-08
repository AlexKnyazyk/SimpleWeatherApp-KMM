package com.simple.weather.app.android.presentation.ui.base.weather.model

data class DetailedWeatherUi(
    val windKph: Double,
    val windMph: Double,
    val windDir: String,
    val humidity: Int,
    val pressureMb: Double,
    val visibilityKm: Double,
    val visibilityMiles: Double,
    val indexUv: Int,
)