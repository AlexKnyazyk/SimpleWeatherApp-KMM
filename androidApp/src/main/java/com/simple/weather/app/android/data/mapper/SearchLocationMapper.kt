package com.simple.weather.app.android.data.mapper

import com.simple.weather.app.android.data.model.response.SearchLocation
import com.simple.weather.app.android.domain.model.SearchLocationModel

internal fun SearchLocation.toDomain() = SearchLocationModel(
    id = id,
    lat = lat,
    lon = lon,
    name = name,
    region = region,
    country = country
)