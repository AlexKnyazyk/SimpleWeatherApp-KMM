package com.simple.weather.app.data.repository.location

import com.simple.weather.app.data.model.LocationResult
import com.simple.weather.app.domain.repository.DeviceLocationRepository

internal actual class DeviceLocationRepositoryImpl : DeviceLocationRepository {

    override fun getLocation(): LocationResult {
        return LocationResult.NoLocation
    }
}