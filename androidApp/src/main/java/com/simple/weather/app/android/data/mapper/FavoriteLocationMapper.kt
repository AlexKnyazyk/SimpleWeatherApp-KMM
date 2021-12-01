package com.simple.weather.app.android.data.mapper

import com.simple.weather.app.android.data.FavoriteLocationDb
import com.simple.weather.app.android.domain.model.FavoriteLocationModel

internal fun FavoriteLocationDb.toDomain() = FavoriteLocationModel(
    id = id,
    name = name,
    region = region,
    country = country,
    tempC = temp_c?.toInt(),
    tempF = temp_f?.toInt(),
    weatherConditionIconUrl = condition_icon_url
)

internal fun FavoriteLocationModel.toDb() = FavoriteLocationDb(
    id = id,
    name = name,
    region = region,
    country = country,
    temp_c = tempC?.toFloat(),
    temp_f = tempF?.toFloat(),
    condition_icon_url = weatherConditionIconUrl
)