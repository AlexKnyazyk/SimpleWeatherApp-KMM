package com.simple.weather.app.android.domain.model

data class SearchLocationModel(
    val id: Int,
    val name: String,
    val region: String,
    val country: String
)