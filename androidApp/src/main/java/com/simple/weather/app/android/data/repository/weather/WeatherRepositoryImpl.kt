package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.model.WeatherData
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class WeatherRepositoryImpl(
    private val httpClient: HttpClient
) : WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherData> = runCatching {
        httpClient.get<WeatherData> {
            url("https://api.weatherapi.com/v1/forecast.json")
            parameter("q", "$lat,$lon")
            parameter("days", 5)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun getCurrentWeather(ipv6: String): Result<WeatherData> = runCatching {
        httpClient.get<WeatherData> {
            url("https://api.weatherapi.com/v1/forecast.json")
            parameter("q", ipv6)
            parameter("days", 5)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }
}