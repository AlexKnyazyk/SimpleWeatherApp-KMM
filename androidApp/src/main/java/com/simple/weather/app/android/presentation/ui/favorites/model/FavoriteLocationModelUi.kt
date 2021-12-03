package com.simple.weather.app.android.presentation.ui.favorites.model

import com.simple.weather.app.utils.DateTimestamp

data class FavoriteLocationModelUi(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val tempC: Int?,
    val tempF: Int?,
    val weatherConditionIconUrl: String?,
    val updateTimestamp: DateTimestamp?,
    val isTempMetric: Boolean
)