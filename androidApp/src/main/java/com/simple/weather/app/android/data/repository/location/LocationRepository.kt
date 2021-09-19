package com.simple.weather.app.android.data.repository.location

import com.simple.weather.app.android.data.model.LocationResult

interface LocationRepository {

    fun getLocation(): LocationResult

}