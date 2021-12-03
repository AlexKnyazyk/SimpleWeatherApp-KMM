package com.simple.weather.app.domain.domain.repository

import com.simple.weather.app.domain.domain.model.SettingsUnitsModel
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val settingsUnitsModelFlow: Flow<SettingsUnitsModel>

    suspend fun setTempMetric(isTempMetric: Boolean)

    suspend fun setDistanceMetric(isDistanceMetric: Boolean)

}