package com.simple.weather.app.domain.domain.model

data class SettingsModel(
    val isTempMetric: Boolean = true,
    val isDistanceMetric: Boolean = true
)