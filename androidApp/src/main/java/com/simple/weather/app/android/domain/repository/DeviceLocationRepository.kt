package com.simple.weather.app.android.domain.repository

import com.simple.weather.app.android.data.model.LocationResult

interface DeviceLocationRepository {

    fun getLocation(): LocationResult

}