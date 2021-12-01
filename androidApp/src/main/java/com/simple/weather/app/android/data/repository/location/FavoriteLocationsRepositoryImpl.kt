package com.simple.weather.app.android.data.repository.location

import com.simple.weather.app.android.data.datasource.local.FavoriteLocationsLocalDataSource
import com.simple.weather.app.android.data.mapper.toDb
import com.simple.weather.app.android.data.mapper.toDomain
import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import com.simple.weather.app.android.domain.repository.FavoriteLocationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

internal class FavoriteLocationsRepositoryImpl(
    private val favoriteLocationsLocalDataSource: FavoriteLocationsLocalDataSource
) : FavoriteLocationsRepository {

    override fun allFavoriteLocations(): Flow<List<FavoriteLocationModel>> {
        return favoriteLocationsLocalDataSource.allFavoriteLocations()
            .map { list ->
                list.map { it.toDomain() }
            }
    }

    override suspend fun addOrUpdate(model: FavoriteLocationModel) = withContext(Dispatchers.IO) {
        favoriteLocationsLocalDataSource.insertOrReplace(model.toDb())
    }

    override suspend fun delete(model: FavoriteLocationModel) = withContext(Dispatchers.IO) {
        favoriteLocationsLocalDataSource.delete(model.id)
    }
}
