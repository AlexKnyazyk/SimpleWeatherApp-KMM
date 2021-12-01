package com.simple.weather.app.android.data.datasource.local

import com.simple.weather.app.android.data.FavoriteLocationDb
import kotlinx.coroutines.flow.Flow

internal interface FavoriteLocationsLocalDataSource {

    fun allFavoriteLocations(): Flow<List<FavoriteLocationDb>>

    suspend fun insertOrReplace(data: FavoriteLocationDb)

    suspend fun delete(id: Int)
}