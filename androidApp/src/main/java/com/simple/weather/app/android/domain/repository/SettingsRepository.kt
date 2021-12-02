package com.simple.weather.app.android.domain.repository

import com.simple.weather.app.android.domain.model.SettingsModel
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val settingsModelFlow: Flow<SettingsModel>

    suspend fun setTempMetric(isTempMetric: Boolean)

    suspend fun setDistanceMetric(isDistanceMetric: Boolean)

}