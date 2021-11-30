package com.simple.weather.app.android.data.model.request

sealed class WeatherRequest {
    class Location(val lat: Double, val lon: Double) : WeatherRequest()
    object AutoIPAddress : WeatherRequest()
}