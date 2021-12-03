package com.simple.weather.app.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDayData(
    @SerialName("maxtemp_c")
    val maxtempC: Double, // 16.1
    @SerialName("maxtemp_f")
    val maxtempF: Double, // 61.0
    @SerialName("mintemp_c")
    val mintempC: Double, // 8.6
    @SerialName("mintemp_f")
    val mintempF: Double, // 47.5
    @SerialName("avgtemp_c")
    val avgtempC: Double, // 11.6
    @SerialName("avgtemp_f")
    val avgtempF: Double, // 52.9
    @SerialName("maxwind_mph")
    val maxwindMph: Double, // 10.7
    @SerialName("maxwind_kph")
    val maxwindKph: Double, // 17.3
    @SerialName("totalprecip_mm")
    val totalprecipMm: Double, // 0.0
    @SerialName("totalprecip_in")
    val totalprecipIn: Double, // 0.0
    @SerialName("avgvis_km")
    val avgvisKm: Double, // 10.0
    @SerialName("avgvis_miles")
    val avgvisMiles: Double, // 6.0
    @SerialName("avghumidity")
    val avghumidity: Double, // 66.0
    @SerialName("daily_will_it_rain")
    val dailyWillItRain: Int, // 0
    @SerialName("daily_chance_of_rain")
    val dailyChanceOfRain: Int, // 0
    @SerialName("daily_will_it_snow")
    val dailyWillItSnow: Int, // 0
    @SerialName("daily_chance_of_snow")
    val dailyChanceOfSnow: Int, // 0
    @SerialName("condition")
    val condition: WeatherCondition,
    @SerialName("uv")
    val uv: Double // 3.0
)