package com.simple.weather.app.data.model.response

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



