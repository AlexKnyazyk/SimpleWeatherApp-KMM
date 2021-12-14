package com.simple.weather.app.data.datasource.remote

import com.simple.weather.app.data.model.response.ErrorResponse
import com.simple.weather.app.data.model.response.SearchLocation
import com.simple.weather.app.data.model.response.WeatherData
import com.simple.weather.app.domain.domain.model.errors.NoInternetConnectionError
import com.simple.weather.app.domain.domain.model.errors.ServerError
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class WeatherRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : WeatherRemoteDataSource {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherData> = runRequestCatching {
        httpClient.get {
            url(FORECAST_URL)
            parameter("q", "$lat,$lon")
            parameter("days", FORECAST_DAYS)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun getCurrentWeather(locationName: String): Result<WeatherData> = runRequestCatching {
        httpClient.get {
            url(FORECAST_URL)
            parameter("q", locationName)
            parameter("days", FORECAST_DAYS)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun getCurrentWeatherByAutoIp(): Result<WeatherData> = runRequestCatching {
        httpClient.get {
            url(FORECAST_URL)
            parameter("q", "auto:ip")
            parameter("days", FORECAST_DAYS)
            parameter("alerts", "no")
            parameter("aqi", "no")
        }
    }

    override suspend fun searchLocation(query: String): Result<List<SearchLocation>> = runRequestCatching {
        httpClient.get {
            url(SEARCH_URL)
            parameter("q", query)
        }
    }

    private suspend inline fun <T, R> T.runRequestCatching(block: T.() -> R): Result<R> {
        return try {
            Result.success(block())
        } catch (e: UnknownHostException) {
            Result.failure(NoInternetConnectionError())
        } catch (e: SocketTimeoutException) {
            Result.failure(NoInternetConnectionError())
        } catch (e: HttpRequestTimeoutException) {
            Result.failure(NoInternetConnectionError())
        } catch (e: ClientRequestException) {
            val errorResponse = e.response.readText().let { errorJson ->
                Json.decodeFromString<ErrorResponse>(errorJson)
            }
            Result.failure(ServerError(code = e.response.status.value, message = errorResponse.error?.message))
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }

    companion object {
        private const val BASE_URL = "https://api.weatherapi.com/v1"
        private const val FORECAST_URL = "$BASE_URL/forecast.json"
        private const val SEARCH_URL = "$BASE_URL/search.json"
        private const val FORECAST_DAYS = 7
    }
}