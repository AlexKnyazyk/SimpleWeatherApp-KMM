package com.simple.weather.app.data.repository.settings

import com.simple.weather.app.data.datasource.local.SettingsLocalDataSource
import com.simple.weather.app.domain.domain.model.SettingsUnitsModel
import com.simple.weather.app.domain.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow

internal class SettingsRepositoryImpl(
    private val settingsLocalDataSource: SettingsLocalDataSource
) : SettingsRepository {

    override val settingsUnitsModelFlow = MutableStateFlow(
        SettingsUnitsModel(
            isTempMetric = settingsLocalDataSource.isTempMetric,
            isDistanceMetric = settingsLocalDataSource.isDistanceMetric
        )
    )

    override suspend fun setTempMetric(isTempMetric: Boolean) {
        settingsLocalDataSource.isTempMetric = isTempMetric
        settingsUnitsModelFlow.emit(settingsUnitsModelFlow.value.copy(isTempMetric = isTempMetric))
    }

    override suspend fun setDistanceMetric(isDistanceMetric: Boolean) {
        settingsLocalDataSource.isDistanceMetric = isDistanceMetric
        settingsUnitsModelFlow.emit(settingsUnitsModelFlow.value.copy(isDistanceMetric = isDistanceMetric))
    }
}