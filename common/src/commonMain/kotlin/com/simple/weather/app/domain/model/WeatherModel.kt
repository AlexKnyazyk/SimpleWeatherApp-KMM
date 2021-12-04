package com.simple.weather.app.domain.model

data class WeatherModel(
    val locationName: String,
    val locationCountry: String,
    val lastUpdated: String,
    val tempC: Int,
    val tempF: Int,
    val tempFeelsLikeC: Int,
    val tempFeelsLikeF: Int,
    val weatherCondition: String,
    val weatherConditionIconUrl: String,
    val windKph: Double,
    val windMph: Double,
    val windDir: String,
    val humidity: Int,
    val pressureMb: Double,
    val visibilityKm: Double,
    val visibilityMiles: Double,
    val indexUv: Int,
    val forecastDaily: List<ForecastModel.Day>,
    val forecastHourly: List<ForecastModel.Hour>,
)