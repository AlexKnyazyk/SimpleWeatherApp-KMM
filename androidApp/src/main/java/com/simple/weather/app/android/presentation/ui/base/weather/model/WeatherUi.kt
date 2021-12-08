package com.simple.weather.app.android.presentation.ui.base.weather.model

data class WeatherUi(
    val currentWeather: CurrentWeatherUi,
    val forecastWeather: ForecastWeatherUi,
    val detailedWeather: DetailedWeatherUi,
    val settingsUnits: SettingsUnitsUi
)