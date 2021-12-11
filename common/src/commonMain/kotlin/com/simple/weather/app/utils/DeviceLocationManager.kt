package com.simple.weather.app.utils

import com.simple.weather.app.data.model.LocationResult

/**
 * Should be implemented on specific platform and injected into Koin DI
 */

interface DeviceLocationManager {

    fun getLocation(): LocationResult

}