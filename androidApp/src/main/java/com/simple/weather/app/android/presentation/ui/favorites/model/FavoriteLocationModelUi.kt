package com.simple.weather.app.android.presentation.ui.favorites.model

import java.util.*

data class FavoriteLocationModelUi(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val tempC: Int?,
    val tempF: Int?,
    val weatherConditionIconUrl: String?,
    val updateTimestamp: Calendar?,
    val isTempMetric: Boolean
)