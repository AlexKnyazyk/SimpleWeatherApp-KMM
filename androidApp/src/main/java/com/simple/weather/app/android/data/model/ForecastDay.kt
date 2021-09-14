package com.simple.weather.app.android.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastDay(
    @SerialName("date")
    val date: String, // 2021-09-14
    @SerialName("date_epoch")
    val dateEpoch: Long, // 1631577600
    @SerialName("day")
    val day: ForecastDayData,
    @SerialName("astro")
    val astro: ForecastAstro,
    @SerialName("hour")
    val hour: List<ForecastDayHourData>
)