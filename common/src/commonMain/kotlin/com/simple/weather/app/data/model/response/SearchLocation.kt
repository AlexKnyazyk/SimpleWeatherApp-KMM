package com.simple.weather.app.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchLocation(
    @SerialName("id")
    val id: Int, // 2801268
    @SerialName("lat")
    val lat: Double, // 51.52
    @SerialName("lon")
    val lon: Double, // -0.11
    @SerialName("url")
    val url: String, // london-city-of-london-greater-london-united-kingdom
    @SerialName("name")
    val name: String, // London, City of London, Greater London, United Kingdom
    @SerialName("region")
    val region: String, // City of London, Greater London
    @SerialName("country")
    val country: String, // United Kingdom
)