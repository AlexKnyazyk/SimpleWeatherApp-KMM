package com.simple.weather.app.android.data.datasource.local

import android.content.Context

internal class SettingsLocalDataSourceImpl(
    context: Context
) : SettingsLocalDataSource {

    private val preferences = context.getSharedPreferences(SETTINGS_PREFERENCES, Context.MODE_PRIVATE)

    override var isTempMetric: Boolean
        get() = preferences.getBoolean(KEY_TEMP_METRIC, true)
        set(value) = preferences.edit().putBoolean(KEY_TEMP_METRIC, value).apply()

    override var isDistanceMetric: Boolean
        get() = preferences.getBoolean(KEY_DISTANCE_METRIC, true)
        set(value) = preferences.edit().putBoolean(KEY_DISTANCE_METRIC, value).apply()

    companion object {
        private const val SETTINGS_PREFERENCES = "Settings"
        private const val KEY_TEMP_METRIC = "temp_metric"
        private const val KEY_DISTANCE_METRIC = "distance_metric"
    }
}