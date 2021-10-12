package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.model.response.SearchLocation
import com.simple.weather.app.android.data.model.response.WeatherData
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class WeatherRepositoryImpl(
    private val httpClient: HttpClient
) : WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherData> = runCatching {
        httpClient.get<WeatherData> {
            url(FORECAST_URL)
            parameter("q", "$lat,$lon")
            parameter("days", FORECAST_DAYS)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun getCurrentWeather(ipv6: String): Result<WeatherData> = runCatching {
        httpClient.get<WeatherData> {
            url(FORECAST_URL)
            parameter("q", ipv6)
            parameter("days", FORECAST_DAYS)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun searchLocation(query: String): Result<List<SearchLocation>> = runCatching {
        httpClient.get<List<SearchLocation>> {
            url(SEARCH_URL)
            parameter("q", query)
        }
    }

    companion object {
        private const val BASE_URL = "https://api.weatherapi.com/v1"
        private const val FORECAST_URL = "$BASE_URL/forecast.json"
        private const val SEARCH_URL = "$BASE_URL/search.json"
        private const val FORECAST_DAYS = 7
    }
}