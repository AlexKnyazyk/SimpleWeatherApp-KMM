package com.simple.weather.app.android.presentation.ui.base.weather.model

data class WeatherModelUi(
    val currentWeather: CurrentWeatherUi,
    val forecastWeather: ForecastWeatherUi,
    val detailedWeather: DetailedWeatherUi,
    val settingsUnits: SettingsUnitsUi
)