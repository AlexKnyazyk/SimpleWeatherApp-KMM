package com.simple.weather.app.android.data.repository.weather

import com.simple.weather.app.android.data.datasource.remote.WeatherRemoteDataSource
import com.simple.weather.app.android.data.mapper.toDomain
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.domain.repository.WeatherRepository

internal class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource
) : WeatherRepository {

    override suspend fun getCurrentWeather(lat: Double, lon: Double): Result<WeatherModel> {
        return weatherRemoteDataSource.getCurrentWeather(lat, lon).map { it.toDomain() }
    }

    override suspend fun getCurrentWeather(ipv6: String): Result<WeatherModel> {
        return weatherRemoteDataSource.getCurrentWeather(ipv6).map { it.toDomain() }
    }

    override suspend fun searchLocation(query: String): Result<List<SearchLocationModel>> {
        return weatherRemoteDataSource.searchLocation(query).map { list -> list.map { it.toDomain() } }
    }
}