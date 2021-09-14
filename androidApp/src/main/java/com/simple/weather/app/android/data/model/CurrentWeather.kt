package com.simple.weather.app.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeather(
    @SerialName("last_updated_epoch")
    val lastUpdatedEpoch: Int, // 1631611800
    @SerialName("last_updated")
    val lastUpdated: String, // 2021-09-14 12:30
    @SerialName("temp_c")
    val tempC: Double, // 13.0
    @SerialName("temp_f")
    val tempF: Double, // 55.4
    @SerialName("is_day")
    val isDay: Int, // 1
    @SerialName("condition")
    val condition: WeatherCondition,
    @SerialName("wind_mph")
    val windMph: Double, // 9.4
    @SerialName("wind_kph")
    val windKph: Double, // 15.1
    @SerialName("wind_degree")
    val windDegree: Int, // 330
    @SerialName("wind_dir")
    val windDir: String, // NNW
    @SerialName("pressure_mb")
    val pressureMb: Double, // 1017.0
    @SerialName("pressure_in")
    val pressureIn: Double, // 30.03
    @SerialName("precip_mm")
    val precipMm: Double, // 0.0
    @SerialName("precip_in")
    val precipIn: Double, // 0.0
    @SerialName("humidity")
    val humidity: Int, // 54
    @SerialName("cloud")
    val cloud: Int, // 0
    @SerialName("feelslike_c")
    val feelslikeC: Double, // 11.4
    @SerialName("feelslike_f")
    val feelslikeF: Double, // 52.6
    @SerialName("vis_km")
    val visKm: Double, // 10.0
    @SerialName("vis_miles")
    val visMiles: Double, // 6.0
    @SerialName("uv")
    val uv: Double, // 4.0
    @SerialName("gust_mph")
    val gustMph: Double, // 13.0
    @SerialName("gust_kph")
    val gustKph: Double // 20.9
)