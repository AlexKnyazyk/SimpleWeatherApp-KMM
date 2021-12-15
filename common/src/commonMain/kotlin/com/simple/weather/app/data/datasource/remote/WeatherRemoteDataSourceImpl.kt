package com.simple.weather.app.data.datasource.remote

import com.simple.weather.app.data.model.CResult
import com.simple.weather.app.data.model.response.ErrorResponse
import com.simple.weather.app.data.model.response.SearchLocation
import com.simple.weather.app.data.model.response.WeatherData
import com.simple.weather.app.domain.model.errors.NoInternetConnectionError
import com.simple.weather.app.domain.model.errors.ServerError
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class WeatherRemoteDataSourceImpl(
    private val httpClient: HttpClient,
) : WeatherRemoteDataSource {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): CResult<WeatherData> {
        return runRequestCatching {
            httpClient.get {
                url(FORECAST_URL)
                parameter("q", "$lat,$lon")
                parameter("days", FORECAST_DAYS)
                parameter("alerts", "no")
                parameter("aqi", "no")
            }
        }
    }

    override suspend fun getCurrentWeather(locationName: String): CResult<WeatherData> {
        return runRequestCatching {
            httpClient.get {
                url(FORECAST_URL)
                parameter("q", locationName)
                parameter("days", FORECAST_DAYS)
                parameter("alerts", "no")
                parameter("aqi", "no")
            }
        }
    }

    override suspend fun getCurrentWeatherByAutoIp(): CResult<WeatherData> {
        return runRequestCatching {
            httpClient.get {
                url(FORECAST_URL)
                parameter("q", "auto:ip")
                parameter("days", FORECAST_DAYS)
                parameter("alerts", "no")
                parameter("aqi", "no")
            }
        }
    }

    override suspend fun searchLocation(query: String): CResult<List<SearchLocation>> {
        return runRequestCatching {
            httpClient.get {
                url(SEARCH_URL)
                parameter("q", query)
            }
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    private suspend inline fun <T, R> T.runRequestCatching(block: T.() -> R): CResult<R> {
        return try {
            CResult.success(block())
        } catch (e: UnknownHostException) {
            CResult.failure(NoInternetConnectionError())
        } catch (e: SocketTimeoutException) {
            CResult.failure(NoInternetConnectionError())
        } catch (e: HttpRequestTimeoutException) {
            CResult.failure(NoInternetConnectionError())
        } catch (e: ClientRequestException) {
            val errorResponse = e.response.readText().let { errorJson ->
                Json.decodeFromString<ErrorResponse>(errorJson)
            }
            CResult.failure(
                ServerError(code = e.response.status.value, message = errorResponse.error?.message)
            )
        } catch (e: Throwable) {
            CResult.failure(e)
        }
    }

    companion object {
        private const val BASE_URL = "https://api.weatherapi.com/v1"
        private const val FORECAST_URL = "$BASE_URL/forecast.json"
        private const val SEARCH_URL = "$BASE_URL/search.json"
        private const val FORECAST_DAYS = 7
    }
}