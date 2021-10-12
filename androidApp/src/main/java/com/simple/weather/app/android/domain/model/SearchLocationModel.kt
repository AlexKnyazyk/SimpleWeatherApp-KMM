package com.simple.weather.app.android.domain.model

data class SearchLocationModel(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val name: String,
    val region: String,
    val country: String
)