package com.simple.weather.app.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayHourData(
    @SerialName("time_epoch")
    val timeEpoch: Long, // 1631566800
    @SerialName("time")
    val time: String, // 2021-09-14 00:00
    @SerialName("temp_c")
    val tempC: Double, // 14.2
    @SerialName("temp_f")
    val tempF: Double, // 57.6
    @SerialName("is_day")
    val isDay: Int, // 0
    @SerialName("condition")
    val condition: WeatherCondition,
    @SerialName("wind_mph")
    val windMph: Double, // 8.9
    @SerialName("wind_kph")
    val windKph: Double, // 14.4
    @SerialName("wind_degree")
    val windDegree: Int, // 325
    @SerialName("wind_dir")
    val windDir: String, // NW
    @SerialName("pressure_mb")
    val pressureMb: Double, // 1014.0
    @SerialName("pressure_in")
    val pressureIn: Double, // 29.93
    @SerialName("precip_mm")
    val precipMm: Double, // 0.0
    @SerialName("precip_in")
    val precipIn: Double, // 0.0
    @SerialName("humidity")
    val humidity: Int, // 88
    @SerialName("cloud")
    val cloud: Int, // 83
    @SerialName("feelslike_c")
    val feelslikeC: Double, // 13.2
    @SerialName("feelslike_f")
    val feelslikeF: Double, // 55.8
    @SerialName("windchill_c")
    val windchillC: Double, // 13.2
    @SerialName("windchill_f")
    val windchillF: Double, // 55.8
    @SerialName("heatindex_c")
    val heatindexC: Double, // 14.2
    @SerialName("heatindex_f")
    val heatindexF: Double, // 57.6
    @SerialName("dewpoint_c")
    val dewpointC: Double, // 12.3
    @SerialName("dewpoint_f")
    val dewpointF: Double, // 54.1
    @SerialName("will_it_rain")
    val willItRain: Int, // 0
    @SerialName("chance_of_rain")
    val chanceOfRain: Int, // 0
    @SerialName("will_it_snow")
    val willItSnow: Int, // 0
    @SerialName("chance_of_snow")
    val chanceOfSnow: Int, // 0
    @SerialName("vis_km")
    val visKm: Double, // 10.0
    @SerialName("vis_miles")
    val visMiles: Double, // 6.0
    @SerialName("gust_mph")
    val gustMph: Double, // 13.0
    @SerialName("gust_kph")
    val gustKph: Double, // 20.9
    @SerialName("uv")
    val uv: Double // 1.0
)