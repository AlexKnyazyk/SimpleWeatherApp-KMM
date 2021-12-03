package com.simple.weather.app.data.datasource.remote

import com.simple.weather.app.data.model.response.SearchLocation
import com.simple.weather.app.data.model.response.WeatherData
import io.ktor.client.*
import io.ktor.client.request.*

internal class WeatherRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : WeatherRemoteDataSource {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherData> = runCatching {
        httpClient.get {
            url(FORECAST_URL)
            parameter("q", "$lat,$lon")
            parameter("days", FORECAST_DAYS)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun getCurrentWeather(locationName: String): Result<WeatherData> = runCatching {
        httpClient.get {
            url(FORECAST_URL)
            parameter("q", locationName)
            parameter("days", FORECAST_DAYS)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun getCurrentWeatherByAutoIp(): Result<WeatherData> = runCatching {
        httpClient.get {
            url(FORECAST_URL)
            parameter("q", "auto:ip")
            parameter("days", FORECAST_DAYS)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun searchLocation(query: String): Result<List<SearchLocation>> = runCatching {
        httpClient.get {
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