package com.simple.weather.app.domain.domain.repository

import com.simple.weather.app.domain.domain.model.FavoriteLocationModel
import kotlinx.coroutines.flow.Flow

interface FavoriteLocationsRepository {

    fun allFavoriteLocations(): Flow<List<FavoriteLocationModel>>

    suspend fun getById(id: Int): FavoriteLocationModel?

    suspend fun addOrUpdate(model: FavoriteLocationModel)

    suspend fun deleteById(id: Int)
}