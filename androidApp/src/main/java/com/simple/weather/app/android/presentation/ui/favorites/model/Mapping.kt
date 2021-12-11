package com.simple.weather.app.android.presentation.ui.favorites.model

import com.simple.weather.app.domain.domain.model.FavoriteLocationModel

fun FavoriteLocationModel.toUi(isTempMetric: Boolean) = FavoriteLocationItemUi(
    id = id,
    name = name,
    region = region,
    country = country,
    tempC = tempC,
    tempF = tempF,
    weatherConditionIconUrl = weatherConditionIconUrl,
    isTempMetric = isTempMetric
)