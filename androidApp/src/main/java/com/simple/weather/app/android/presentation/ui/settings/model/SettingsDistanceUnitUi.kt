package com.simple.weather.app.android.presentation.ui.settings.model

import androidx.annotation.StringRes
import com.simple.weather.app.android.R

enum class SettingsDistanceUnitUi(@StringRes val uiValue: Int) {
    KM(R.string.settings_distance_km),
    MILES(R.string.settings_distance_miles)
}