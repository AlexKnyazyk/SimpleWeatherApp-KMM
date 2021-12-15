package com.simple.weather.app.android.presentation.ui.base.weather.model

import com.simple.weather.app.domain.model.SettingsUnitsModel
import com.simple.weather.app.domain.model.WeatherModel

fun WeatherModel.toUi(settings: SettingsUnitsUi) = WeatherModelUi(
    currentWeather = CurrentWeatherUi(
        locationName = this.locationName,
        locationCountry = this.locationCountry,
        lastUpdated = this.lastUpdated,
        tempC = this.tempC,
        tempF = this.tempF,
        tempFeelsLikeC = this.tempFeelsLikeC,
        tempFeelsLikeF = this.tempFeelsLikeF,
        weatherConditionIconUrl = this.weatherConditionIconUrl,
        weatherCondition = this.weatherCondition
    ),
    forecastWeather = ForecastWeatherUi(
        forecastDaily = this.forecastDaily,
        forecastHourly = this.forecastHourly
    ),
    detailedWeather = DetailedWeatherUi(
        windKph = this.windKph,
        windMph = this.windMph,
        windDir = this.windDir,
        humidity = this.humidity,
        pressureMb = this.pressureMb,
        visibilityKm = this.visibilityKm,
        visibilityMiles = this.visibilityMiles,
        indexUv = this.indexUv
    ),
    settingsUnits = settings
)

fun SettingsUnitsModel.toUi() = SettingsUnitsUi(
    isTempMetric = this.isTempMetric,
    isDistanceMetric = this.isDistanceMetric
)