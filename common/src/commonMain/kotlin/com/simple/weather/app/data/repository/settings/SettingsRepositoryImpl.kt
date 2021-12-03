package com.simple.weather.app.data.repository.settings

import com.simple.weather.app.data.datasource.local.SettingsLocalDataSource
import com.simple.weather.app.domain.domain.model.SettingsModel
import com.simple.weather.app.domain.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow

internal class SettingsRepositoryImpl(
    private val settingsLocalDataSource: SettingsLocalDataSource
) : SettingsRepository {

    override val settingsModelFlow = MutableStateFlow(
        SettingsModel(
            isTempMetric = settingsLocalDataSource.isTempMetric,
            isDistanceMetric = settingsLocalDataSource.isDistanceMetric
        )
    )

    override suspend fun setTempMetric(isTempMetric: Boolean) {
        settingsLocalDataSource.isTempMetric = isTempMetric
        settingsModelFlow.emit(settingsModelFlow.value.copy(isTempMetric = isTempMetric))
    }

    override suspend fun setDistanceMetric(isDistanceMetric: Boolean) {
        settingsLocalDataSource.isDistanceMetric = isDistanceMetric
        settingsModelFlow.emit(settingsModelFlow.value.copy(isDistanceMetric = isDistanceMetric))
    }
}