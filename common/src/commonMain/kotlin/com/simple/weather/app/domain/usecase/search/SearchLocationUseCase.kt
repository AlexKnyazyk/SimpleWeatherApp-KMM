package com.simple.weather.app.domain.usecase.search

import com.simple.weather.app.data.model.CResult
import com.simple.weather.app.domain.model.SearchLocationModel
import com.simple.weather.app.domain.repository.WeatherRepository

interface ISearchLocationUseCase {
    suspend operator fun invoke(query: String): CResult<List<SearchLocationModel>>
}

internal class SearchLocationUseCase(
    private val weatherRepository: WeatherRepository
) : ISearchLocationUseCase {

    override suspend operator fun invoke(query: String): CResult<List<SearchLocationModel>> {
        return if (query.isNotBlank()) {
            weatherRepository.searchLocation(query)
        } else {
            CResult.success(emptyList())
        }
    }
}