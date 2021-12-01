package com.simple.weather.app.android.data.datasource.local

import com.simple.weather.app.android.Database
import com.simple.weather.app.android.data.FavoriteLocationDb
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.suspendCoroutine

internal class FavoriteLocationsLocalDataSourceImpl(
    private val database: Database
) : FavoriteLocationsLocalDataSource {

    override fun allFavoriteLocations(): Flow<List<FavoriteLocationDb>> {
        return database.favoriteLocationsQueries.selectAll()
            .asFlow()
            .mapToList()
    }

    override suspend fun insertOrReplace(data: FavoriteLocationDb) = suspendCoroutine<Unit> {
        database.favoriteLocationsQueries.insertOrReplace(data)
    }
}