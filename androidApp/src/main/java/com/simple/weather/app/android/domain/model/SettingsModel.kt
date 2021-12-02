package com.simple.weather.app.android.domain.model

data class SettingsModel(
    val isTempMetric: Boolean = true,
    val isDistanceMetric: Boolean = true
)