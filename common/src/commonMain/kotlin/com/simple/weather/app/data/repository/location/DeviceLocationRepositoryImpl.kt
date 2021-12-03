package com.simple.weather.app.data.repository.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import com.simple.weather.app.domain.domain.repository.DeviceLocationRepository
//import com.simple.weather.app.android.utils.isPermissionGranted
import com.simple.weather.app.data.model.LocationResult

//TODO
internal class DeviceLocationRepositoryImpl(
//    private val context: Context
) : DeviceLocationRepository {

    override fun getLocation(): LocationResult {
//        return if (context.isPermissionGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
//            getLastKnownLocation()?.let { location ->
//                LocationResult.Success(location.latitude, location.longitude)
//            } ?: LocationResult.NoLocation
//        } else {
//            LocationResult.NoPermission
//        }
        return LocationResult.NoLocation
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(): Location? {
        return null
//        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
//        val criteria = Criteria()
//        return locationManager?.getBestProvider(criteria, false)?.let { bestProvider ->
//            locationManager.getLastKnownLocation(bestProvider)
//        }
    }
}