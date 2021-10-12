package com.simple.weather.app.android.domain.usecase

import com.simple.weather.app.android.data.repository.weather.WeatherRepository
import com.simple.weather.app.android.domain.mapper.SearchLocationMapper
import com.simple.weather.app.android.domain.model.SearchLocationModel

class SearchLocationUseCase(
    private val weatherRepository: WeatherRepository,
    private val searchLocationMapper: SearchLocationMapper,
) {

    suspend operator fun invoke(query: String) : Result<List<SearchLocationModel>> {
        return if (query.isNotBlank()) {
            weatherRepository.searchLocation(query)
                .map { list -> list.map(searchLocationMapper::map) }
        } else {
            Result.success(emptyList())
        }
    }
}