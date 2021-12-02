package com.simple.weather.app.android.domain.usecase.search

import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.repository.FavoriteLocationsRepository

interface IAddSearchLocationToFavoritesUseCase {
    suspend operator fun invoke(model: SearchLocationModel)
}

internal class AddSearchLocationToFavoritesUseCase(
    private val favoriteLocationsRepository: FavoriteLocationsRepository
) : IAddSearchLocationToFavoritesUseCase {

    override suspend fun invoke(model: SearchLocationModel) {
        val favoriteLocationModel = FavoriteLocationModel(
            model.id,
            model.name,
            model.region,
            model.country,
            tempC = null,
            tempF = null,
            weatherConditionIconUrl = null,
            updateTimestamp = null
        )
        favoriteLocationsRepository.addOrUpdate(favoriteLocationModel)
    }
}