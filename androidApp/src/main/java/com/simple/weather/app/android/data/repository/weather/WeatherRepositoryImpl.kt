package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.model.CurrentWeatherData
import io.ktor.client.*
import io.ktor.client.request.*
import java.lang.Exception

class WeatherRepositoryImpl(
    private val httpClient: HttpClient
) : WeatherRepository {

    override suspend fun getCurrentWeather(): Result<CurrentWeatherData> {
        return try {
            val response = httpClient.get<CurrentWeatherData> {
                url("https://api.weatherapi.com/v1/current.json")
                parameter("q", "Minsk")
            }
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}