package com.simple.weather.app.android.presentation.ui.base.weather.model

import com.simple.weather.app.domain.model.WeatherModel

data class WeatherModelUi(
    val model: WeatherModel,
    val isTempMetric: Boolean = true,
    val isDistanceMetric: Boolean = true
)