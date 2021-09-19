package com.simple.weather.app.android.domain.model

sealed class ForecastModel(
    val date: Long,
    val temperatureC: Int,
    val temperatureF: Int,
    val iconUrl: String,
    val windSpeedKph: Double,
    val windSpeedMph: Double,
) {
    class Day(
        date: Long,
        temperatureC: Int,
        temperatureF: Int,
        val temperatureMaxC: Int,
        val temperatureMaxF: Int,
        val temperatureMinC: Int,
        val temperatureMinF: Int,
        iconUrl: String,
        windSpeedKph: Double,
        windSpeedMph: Double,
    ) : ForecastModel(date, temperatureC, temperatureF, iconUrl, windSpeedKph, windSpeedMph)

    class Hour(
        date: Long,
        temperatureC: Int,
        temperatureF: Int,
        iconUrl: String,
        windSpeedKph: Double,
        windSpeedMph: Double,
        val windDir: String
    ) : ForecastModel(date, temperatureC, temperatureF, iconUrl, windSpeedKph, windSpeedMph)
}