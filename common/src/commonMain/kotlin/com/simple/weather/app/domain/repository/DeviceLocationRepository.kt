package com.simple.weather.app.domain.repository

import com.simple.weather.app.data.model.LocationResult

interface DeviceLocationRepository {

    fun getLocation(): LocationResult

}