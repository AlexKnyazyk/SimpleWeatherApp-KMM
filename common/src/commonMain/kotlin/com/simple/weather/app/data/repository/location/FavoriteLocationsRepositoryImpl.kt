package com.simple.weather.app.data.repository.location

import com.simple.weather.app.data.datasource.local.FavoriteLocationsLocalDataSource
import com.simple.weather.app.data.mapper.toDb
import com.simple.weather.app.data.mapper.toDomain
import com.simple.weather.app.domain.model.FavoriteLocationModel
import com.simple.weather.app.domain.repository.FavoriteLocationsRepository
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

    override suspend fun getById(id: Int): FavoriteLocationModel? = withContext(Dispatchers.Default) {
        favoriteLocationsLocalDataSource.getById(id)?.toDomain()
    }

    override suspend fun addOrUpdate(model: FavoriteLocationModel) = withContext(Dispatchers.Default) {
        favoriteLocationsLocalDataSource.insertOrReplace(model.toDb())
    }

    override suspend fun deleteById(id: Int) = withContext(Dispatchers.Default) {
        favoriteLocationsLocalDataSource.delete(id)
    }
}
