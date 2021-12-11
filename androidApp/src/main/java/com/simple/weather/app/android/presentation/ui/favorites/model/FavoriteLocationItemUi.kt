package com.simple.weather.app.android.presentation.ui.favorites.model

data class FavoriteLocationItemUi(
    val id: Int,
    val name: String,
    val region: String,
    val country: String,
    val tempC: Int?,
    val tempF: Int?,
    val weatherConditionIconUrl: String?,
    val isTempMetric: Boolean
)