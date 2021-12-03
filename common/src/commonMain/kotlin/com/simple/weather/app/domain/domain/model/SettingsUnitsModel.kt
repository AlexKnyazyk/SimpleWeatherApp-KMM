package com.simple.weather.app.domain.domain.model

data class SettingsUnitsModel(
    val isTempMetric: Boolean = true,
    val isDistanceMetric: Boolean = true
)