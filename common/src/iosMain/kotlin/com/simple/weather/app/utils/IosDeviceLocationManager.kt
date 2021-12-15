package com.simple.weather.app.utils

import com.simple.weather.app.data.model.LocationResult

class IosDeviceLocationManager : DeviceLocationManager {

    override fun getLocation(): LocationResult {
        return LocationResult.NoLocation
    }
}