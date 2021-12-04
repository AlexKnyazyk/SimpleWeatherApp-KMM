package com.simple.weather.app.domain.usecase.search

import com.simple.weather.app.data.model.CResult
import com.simple.weather.app.domain.model.FavoriteLocationModel
import com.simple.weather.app.domain.model.SearchLocationModel
import com.simple.weather.app.domain.model.errors.ExistedFavoriteLocationError
import com.simple.weather.app.domain.repository.FavoriteLocationsRepository

interface IAddSearchLocationToFavoritesUseCase {
    suspend operator fun invoke(model: SearchLocationModel): CResult<Unit>
}

internal class AddSearchLocationToFavoritesUseCase(
    private val favoriteLocationsRepository: FavoriteLocationsRepository
) : IAddSearchLocationToFavoritesUseCase {

    override suspend fun invoke(model: SearchLocationModel): CResult<Unit> {
        val existed = favoriteLocationsRepository.getById(model.id)
        return if (existed != null) {
            CResult.failure(ExistedFavoriteLocationError())
        } else {
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
            CResult.success(Unit)
        }
    }
}