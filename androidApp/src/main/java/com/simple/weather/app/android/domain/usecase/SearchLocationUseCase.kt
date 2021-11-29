package com.simple.weather.app.android.domain.usecase

import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.repository.WeatherRepository

interface ISearchLocationUseCase {
    suspend operator fun invoke(query: String): Result<List<SearchLocationModel>>
}

internal class SearchLocationUseCase(
    private val weatherRepository: WeatherRepository
) : ISearchLocationUseCase {

    override suspend operator fun invoke(query: String): Result<List<SearchLocationModel>> {
        return if (query.isNotBlank()) {
            weatherRepository.searchLocation(query)
        } else {
            Result.success(emptyList())
        }
    }
}