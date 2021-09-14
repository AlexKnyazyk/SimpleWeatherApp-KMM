package com.simple.weather.app.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherLocation(
    @SerialName("name")
    val name: String, // Minsk
    @SerialName("region")
    val region: String, // Minsk
    @SerialName("country")
    val country: String, // Belarus
    @SerialName("lat")
    val lat: Double, // 53.9
    @SerialName("lon")
    val lon: Double, // 27.57
    @SerialName("tz_id")
    val tzId: String, // Europe/Minsk
    @SerialName("localtime_epoch")
    val localtimeEpoch: Long, // 1631612195
    @SerialName("localtime")
    val localtime: String // 2021-09-14 12:36
)