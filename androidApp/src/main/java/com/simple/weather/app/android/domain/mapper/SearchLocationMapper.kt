package com.simple.weather.app.android.domain.mapper

import com.simple.weather.app.android.data.model.response.SearchLocation
import com.simple.weather.app.android.domain.model.SearchLocationModel

class SearchLocationMapper {

    fun map(searchLocation: SearchLocation) : SearchLocationModel {
        return SearchLocationModel(
            id = searchLocation.id,
            lat = searchLocation.lat,
            lon = searchLocation.lon,
            name = searchLocation.name,
            region = searchLocation.region,
            country = searchLocation.country
        )
    }
}