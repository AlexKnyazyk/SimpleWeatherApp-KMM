package com.simple.weather.app.data.repository.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.simple.weather.app.data.model.LocationResult
import com.simple.weather.app.domain.domain.repository.DeviceLocationRepository

internal actual class DeviceLocationRepositoryImpl(
    private val context: Context
) : DeviceLocationRepository {

    override fun getLocation(): LocationResult {
        val locationPermissionState =
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
        return if (locationPermissionState == PackageManager.PERMISSION_GRANTED) {
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