package com.simple.weather.app.android.data.model

sealed class LocationResult {
    class Success(val lat: Double, val lon: Double) : LocationResult()
    object NoLocation : LocationResult()
    object NoPermission : LocationResult()
}