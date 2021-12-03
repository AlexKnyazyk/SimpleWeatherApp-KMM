package com.simple.weather.app.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastAstro(
    @SerialName("sunrise")
    val sunrise: String, // 06:41 AM
    @SerialName("sunset")
    val sunset: String, // 07:28 PM
    @SerialName("moonrise")
    val moonrise: String, // 04:26 PM
    @SerialName("moonset")
    val moonset: String, // 11:08 PM
    @SerialName("moon_phase")
    val moonPhase: String, // Waxing Gibbous
    @SerialName("moon_illumination")
    val moonIllumination: String // 62
)