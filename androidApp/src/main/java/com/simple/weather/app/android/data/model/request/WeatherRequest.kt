package com.simple.weather.app.android.data.model.request

sealed class WeatherRequest {
    data class Location(val lat: Double, val lon: Double) : WeatherRequest()
    data class Name(val name: String) : WeatherRequest()
    object AutoIPAddress : WeatherRequest()
}