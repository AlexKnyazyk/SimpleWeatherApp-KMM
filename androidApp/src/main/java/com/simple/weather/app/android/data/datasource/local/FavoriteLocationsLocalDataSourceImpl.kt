package com.simple.weather.app.android.data.datasource.local

import com.simple.weather.app.android.Database
import com.simple.weather.app.android.data.FavoriteLocationDb
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class FavoriteLocationsLocalDataSourceImpl(
    private val database: Database
) : FavoriteLocationsLocalDataSource {

    override fun allFavoriteLocations(): Flow<List<FavoriteLocationDb>> {
        return database.favoriteLocationsQueries.selectAll()
            .asFlow()
            .mapToList()
    }

    override suspend fun getById(id: Int): FavoriteLocationDb? = suspendCoroutine {
        it.resume(database.favoriteLocationsQueries.getById(id).executeAsOneOrNull())
    }

    override suspend fun insertOrReplace(data: FavoriteLocationDb) = suspendCoroutine<Unit> {
        database.favoriteLocationsQueries.insertOrReplace(data)
        it.resume(Unit)
    }

    override suspend fun delete(id: Int) = suspendCoroutine<Unit> {
        database.favoriteLocationsQueries.delete(id)
        it.resume(Unit)
    }
}