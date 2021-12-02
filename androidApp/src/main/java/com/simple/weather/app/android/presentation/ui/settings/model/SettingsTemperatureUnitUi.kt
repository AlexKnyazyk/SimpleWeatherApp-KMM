package com.simple.weather.app.android.presentation.ui.settings.model

import androidx.annotation.StringRes
import com.simple.weather.app.android.R

enum class SettingsTemperatureUnitUi(@StringRes val uiValue: Int) {
    C(R.string.settings_temperature_c),
    F(R.string.settings_temperature_f)
}