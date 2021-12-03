package com.simple.weather.app.data.datasource.local

import com.simple.weather.app.data.FavoriteLocationDb
import kotlinx.coroutines.flow.Flow

internal interface FavoriteLocationsLocalDataSource {

    fun allFavoriteLocations(): Flow<List<FavoriteLocationDb>>

    suspend fun getById(id: Int): FavoriteLocationDb?

    suspend fun insertOrReplace(data: FavoriteLocationDb)

    suspend fun delete(id: Int)
}