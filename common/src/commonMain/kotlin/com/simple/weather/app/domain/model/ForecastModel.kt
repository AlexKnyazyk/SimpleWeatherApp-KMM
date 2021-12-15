package com.simple.weather.app.domain.model

sealed class ForecastModel(
    val dateMillis: Long,
    val temperatureC: Int,
    val temperatureF: Int,
    val iconUrl: String,
    val windSpeedKph: Double,
    val windSpeedMph: Double,
) {
    class Day(
        dateMillis: Long,
        temperatureC: Int,
        temperatureF: Int,
        val temperatureMaxC: Int,
        val temperatureMaxF: Int,
        val temperatureMinC: Int,
        val temperatureMinF: Int,
        iconUrl: String,
        windSpeedKph: Double,
        windSpeedMph: Double,
    ) : ForecastModel(dateMillis, temperatureC, temperatureF, iconUrl, windSpeedKph, windSpeedMph)

    class Hour(
        dateMillis: Long,
        temperatureC: Int,
        temperatureF: Int,
        iconUrl: String,
        windSpeedKph: Double,
        windSpeedMph: Double,
        val windDir: String
    ) : ForecastModel(dateMillis, temperatureC, temperatureF, iconUrl, windSpeedKph, windSpeedMph)
}