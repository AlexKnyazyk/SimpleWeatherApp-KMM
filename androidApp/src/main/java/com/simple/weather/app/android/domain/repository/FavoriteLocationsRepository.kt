package com.simple.weather.app.android.domain.repository

import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import kotlinx.coroutines.flow.Flow

interface FavoriteLocationsRepository {

    fun allFavoriteLocations(): Flow<List<FavoriteLocationModel>>

    suspend fun getById(id: Int): FavoriteLocationModel?

    suspend fun addOrUpdate(model: FavoriteLocationModel)

    suspend fun delete(model: FavoriteLocationModel)
}