package com.simple.weather.app.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherCondition(
    @SerialName("text")
    val text: String, // Overcast
    @SerialName("icon")
    val icon: String, // //cdn.weatherapi.com/weather/64x64/day/122.png
    @SerialName("code")
    val code: Int // 1009
)