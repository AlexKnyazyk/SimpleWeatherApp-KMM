package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.datasource.remote.WeatherRemoteDataSource
import com.simple.weather.app.android.data.mapper.toDomain
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherModel> =
        withContext(Dispatchers.IO) {
            weatherRemoteDataSource.getCurrentWeather(lat, lon).map { it.toDomain() }
        }

    override suspend fun getCurrentWeather(locationName: String): Result<WeatherModel> =
        withContext(Dispatchers.IO) {
            weatherRemoteDataSource.getCurrentWeather(locationName).map { it.toDomain() }
        }

    override suspend fun getCurrentWeatherByAutoIp(): Result<WeatherModel> =
        withContext(Dispatchers.IO) {
            weatherRemoteDataSource.getCurrentWeatherByAutoIp().map { it.toDomain() }
        }

    override suspend fun searchLocation(query: String): Result<List<SearchLocationModel>> =
        withContext(Dispatchers.IO) {
            weatherRemoteDataSource.searchLocation(query)
                .map { list -> list.map { it.toDomain() } }
        }
}