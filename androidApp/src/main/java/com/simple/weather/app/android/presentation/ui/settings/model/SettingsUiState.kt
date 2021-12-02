package com.simple.weather.app.android.presentation.ui.settings.model

data class SettingsUiState(
    val tempUnits: SettingsTemperatureUnitUi = SettingsTemperatureUnitUi.C,
    val distanceUnits: SettingsDistanceUnitUi = SettingsDistanceUnitUi.KM
)