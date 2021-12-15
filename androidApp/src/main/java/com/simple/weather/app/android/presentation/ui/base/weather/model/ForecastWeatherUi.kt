package com.simple.weather.app.android.presentation.ui.base.weather.model

import com.simple.weather.app.domain.model.ForecastModel

data class ForecastWeatherUi(
    val forecastDaily: List<ForecastModel.Day>,
    val forecastHourly: List<ForecastModel.Hour>,
)