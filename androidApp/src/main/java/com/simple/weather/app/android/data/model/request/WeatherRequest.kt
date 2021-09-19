package com.simple.weather.app.android.data.model.request

sealed class WeatherRequest {
    class Location(val lat: Double, val lon: Double) : WeatherRequest()
    class IPAddress(val ipv6: String) : WeatherRequest()
}