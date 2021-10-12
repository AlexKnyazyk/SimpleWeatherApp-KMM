package com.simple.weather.app.android.domain.mapper

import com.simple.weather.app.android.data.model.response.Forecast
import com.simple.weather.app.android.data.model.response.WeatherData
import com.simple.weather.app.android.domain.model.ForecastModel
import com.simple.weather.app.android.domain.model.WeatherModel
import kotlin.math.round

class WeatherMapper {

    fun map(from: WeatherData): WeatherModel {
        val weather = from.current
        return WeatherModel(
            locationName = from.location.name,
            locationCountry = from.location.country,
            lastUpdated = from.current.lastUpdated,
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
            forecastDaily = mapDailyForecast(from.forecast),
            forecastHourly = mapHourlyForecast(from.forecast)
        )
    }

    private fun mapDailyForecast(forecast: Forecast): List<ForecastModel.Day> {
        return forecast.forecastday.map {
            ForecastModel.Day(
                date = it.dateEpoch,
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

    private fun mapHourlyForecast(forecast: Forecast): List<ForecastModel.Hour> {
        return forecast.forecastday.firstOrNull()?.hour?.map {
            ForecastModel.Hour(
                date = it.timeEpoch,
                temperatureC = round(it.tempC).toInt(),
                temperatureF = round(it.tempF).toInt(),
                iconUrl = "https:${it.condition.icon}",
                windSpeedKph = it.windKph,
                windSpeedMph = it.windMph,
                windDir = it.windDir
            )
        }.orEmpty()
    }
}