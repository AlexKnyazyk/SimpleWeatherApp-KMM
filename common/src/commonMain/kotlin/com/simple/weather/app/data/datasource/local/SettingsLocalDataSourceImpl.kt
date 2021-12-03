package com.simple.weather.app.data.datasource.local

import com.simple.weather.app.data.PreferencesManager

internal class SettingsLocalDataSourceImpl(
    private val preferencesManager: PreferencesManager
) : SettingsLocalDataSource {

    override var isTempMetric: Boolean
        get() = preferencesManager.getBoolean(KEY_TEMP_METRIC, true)
        set(value) = preferencesManager.putBoolean(KEY_TEMP_METRIC, value)

    override var isDistanceMetric: Boolean
        get() = preferencesManager.getBoolean(KEY_DISTANCE_METRIC, true)
        set(value) = preferencesManager.putBoolean(KEY_DISTANCE_METRIC, value)

    companion object {
        private const val KEY_TEMP_METRIC = "temp_metric"
        private const val KEY_DISTANCE_METRIC = "distance_metric"
    }
}