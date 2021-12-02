package com.simple.weather.app.android.data.model.request

sealed class WeatherRequest {
    data class Location(val lat: Double, val lon: Double) : WeatherRequest()
    data class FavoriteId(val id: Int) : WeatherRequest()
    object AutoIPAddress : WeatherRequest()
}