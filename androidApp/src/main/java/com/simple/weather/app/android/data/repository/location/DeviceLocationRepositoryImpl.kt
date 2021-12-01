package com.simple.weather.app.android.data.repository.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import com.simple.weather.app.android.data.model.LocationResult
import com.simple.weather.app.android.domain.repository.DeviceLocationRepository
import com.simple.weather.app.android.utils.isPermissionGranted

internal class DeviceLocationRepositoryImpl(
    private val context: Context
) : DeviceLocationRepository {

    override fun getLocation(): LocationResult {
        return if (context.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            getLastKnownLocation()?.let { location ->
                LocationResult.Success(location.latitude, location.longitude)
            } ?: LocationResult.NoLocation
        } else {
            LocationResult.NoPermission
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(): Location? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        val criteria = Criteria()
        return locationManager?.getBestProvider(criteria, false)?.let { bestProvider ->
            locationManager.getLastKnownLocation(bestProvider)
        }
    }
}