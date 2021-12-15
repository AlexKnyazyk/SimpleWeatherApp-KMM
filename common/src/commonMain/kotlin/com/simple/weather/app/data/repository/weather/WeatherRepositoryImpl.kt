package com.simple.weather.app.data.repository.weather

import com.simple.weather.app.data.datasource.remote.WeatherRemoteDataSource
import com.simple.weather.app.data.mapper.toDomain
import com.simple.weather.app.data.model.CResult
import com.simple.weather.app.domain.model.SearchLocationModel
import com.simple.weather.app.domain.model.WeatherModel
import com.simple.weather.app.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): CResult<WeatherModel> =
        withContext(Dispatchers.Default) {
            weatherRemoteDataSource.getCurrentWeather(lat, lon).map { it.toDomain() }
        }

    override suspend fun getCurrentWeather(locationName: String): CResult<WeatherModel> =
        withContext(Dispatchers.Default) {
            weatherRemoteDataSource.getCurrentWeather(locationName).map { it.toDomain() }
        }

    override suspend fun getCurrentWeatherByAutoIp(): CResult<WeatherModel> =
        withContext(Dispatchers.Default) {
            weatherRemoteDataSource.getCurrentWeatherByAutoIp().map { it.toDomain() }
        }

    override suspend fun searchLocation(query: String): CResult<List<SearchLocationModel>> =
        withContext(Dispatchers.Default) {
            weatherRemoteDataSource.searchLocation(query)
                .map { list -> list.map { it.toDomain() } }
        }
}