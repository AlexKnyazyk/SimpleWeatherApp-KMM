package com.simple.weather.app.data.mapper

import com.simple.weather.app.data.model.response.SearchLocation
import com.simple.weather.app.domain.domain.model.SearchLocationModel

internal fun SearchLocation.toDomain() = SearchLocationModel(
    id = id,
    name = name,
    region = region,
    country = country
)