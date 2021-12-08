package com.simple.weather.app.android.presentation.ui.base.weather.model

data class CurrentWeatherUi(
    val locationName: String,
    val locationCountry: String,
    val lastUpdated: String,
    val tempC: Int,
    val tempF: Int,
    val tempFeelsLikeC: Int,
    val tempFeelsLikeF: Int,
    val weatherCondition: String,
    val weatherConditionIconUrl: String,
)