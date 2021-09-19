package com.simple.weather.app.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherData(
    @SerialName("location")
    val location: WeatherLocation,
    @SerialName("current")
    val current: CurrentWeather,
    @SerialName("forecast")
    val forecast: Forecast
)



