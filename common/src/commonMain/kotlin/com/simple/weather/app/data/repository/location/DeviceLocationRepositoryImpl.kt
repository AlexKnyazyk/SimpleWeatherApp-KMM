package com.simple.weather.app.data.repository.location

import com.simple.weather.app.data.model.LocationResult
import com.simple.weather.app.domain.repository.DeviceLocationRepository
import com.simple.weather.app.utils.DeviceLocationManager

internal class DeviceLocationRepositoryImpl(
    private val deviceLocationManager: DeviceLocationManager
) : DeviceLocationRepository {

    override fun getLocation(): LocationResult {
        return deviceLocationManager.getLocation()
    }
}