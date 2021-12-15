package com.simple.weather.app.data.mapper

import com.simple.weather.app.data.model.response.Forecast
import com.simple.weather.app.data.model.response.WeatherData
import com.simple.weather.app.domain.model.ForecastModel
import com.simple.weather.app.domain.model.WeatherModel
import kotlin.math.round

internal fun WeatherData.toDomain(): WeatherModel {
    val weather = this.current
    return WeatherModel(
        locationName = this.location.name,
        locationCountry = this.location.country,
        lastUpdated = this.current.lastUpdated,
        tempC = round(weather.tempC).toInt(),
        tempF = round(weather.tempF).toInt(),
        tempFeelsLikeC = round(weather.feelslikeC).toInt(),
        tempFeelsLikeF = round(weather.feelslikeF).toInt(),
        weatherCondition = weather.condition.text,
        weatherConditionIconUrl = "https:${weather.condition.icon}",
        windKph = weather.windKph,
        windMph = weather.windMph,
        windDir = weather.windDir,
        humidity = weather.humidity,
        pressureMb = weather.pressureMb,
        visibilityKm = weather.visKm,
        visibilityMiles = weather.visMiles,
        indexUv = weather.uv.toInt(),
        forecastDaily = this.forecast.toDailyForecastDomain(),
        forecastHourly = this.forecast.toHourlyForecastDomain()
    )
}

private fun Forecast.toDailyForecastDomain(): List<ForecastModel.Day> {
    return this.forecastday.map {
        ForecastModel.Day(
            dateMillis = it.dateEpoch * 1000,
            temperatureC = round(it.day.avgtempC).toInt(),
            temperatureF = round(it.day.avgtempF).toInt(),
            temperatureMaxC = round(it.day.maxtempC).toInt(),
            temperatureMaxF = round(it.day.maxtempF).toInt(),
            temperatureMinC = round(it.day.mintempC).toInt(),
            temperatureMinF = round(it.day.mintempC).toInt(),
            iconUrl = "https:${it.day.condition.icon}",
            windSpeedKph = it.day.maxwindKph,
            windSpeedMph = it.day.maxwindMph,
        )
    }
}

private fun Forecast.toHourlyForecastDomain(): List<ForecastModel.Hour> {
    return this.forecastday.firstOrNull()?.hour?.map {
        ForecastModel.Hour(
            dateMillis = it.timeEpoch * 1000,
            temperatureC = round(it.tempC).toInt(),
            temperatureF = round(it.tempF).toInt(),
            iconUrl = "https:${it.condition.icon}",
            windSpeedKph = it.windKph,
            windSpeedMph = it.windMph,
            windDir = it.windDir
        )
    }.orEmpty()
}