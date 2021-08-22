package com.simple.weather.app.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherData(
    @SerialName("location") val name: CurrentWeatherLocation,
    @SerialName("current") val current: CurrentWeather,
)

@Serializable
data class CurrentWeatherLocation(
    @SerialName("name") val name: String,
    @SerialName("region") val region: String,
    @SerialName("country") val country: String,
    @SerialName("lat") val lat: Double,
    @SerialName("lon") val lon: Double,
    @SerialName("tz_id") val tzId: String,
    @SerialName("localtime_epoch") val localtimeEpoch: Long,
    @SerialName("localtime") val localtime: String,
)

@Serializable
data class CurrentWeather(
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("temp_c") val tempC: Float,
    @SerialName("temp_f") val tempF: Float,
    @SerialName("is_day") val isDay: Int,
    @SerialName("condition") val condition: CurrentWeatherCondition,
    @SerialName("wind_mph") val windMph: Float,
    @SerialName("wind_kph") val windKph: Float,
    @SerialName("wind_degree") val windDegree: Int,
    @SerialName("wind_dir") val windDir: String,
    @SerialName("pressure_mb") val pressureMb: Float,
    @SerialName("pressure_in") val pressureIn: Float,
    @SerialName("precip_mm") val precipMm: Float,
    @SerialName("precip_in") val precipIn: Float,
    @SerialName("humidity") val humidity: Int,
    @SerialName("cloud") val cloud: Int,
    @SerialName("feelslike_c") val feelslikeC: Float,
    @SerialName("feelslike_f") val feelslikeF: Float,
    @SerialName("vis_km") val visKm: Float,
    @SerialName("vis_miles") val visMiles: Float,
    @SerialName("uv") val uv: Float,
    @SerialName("gust_mph") val gustMph: Float,
    @SerialName("gust_kph") val gustKph: Float
)

@Serializable
data class CurrentWeatherCondition(
    @SerialName("text") val text: String,
    @SerialName("icon") val icon: String,
    @SerialName("code") val code: Int,
)