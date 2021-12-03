package com.simple.weather.app.domain.domain.model

import com.simple.weather.app.utils.DateTimestamp

data class FavoriteLocationModel(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val tempC: Int?,
    val tempF: Int?,
    val weatherConditionIconUrl: String?,
    val updateTimestamp: DateTimestamp?,
)