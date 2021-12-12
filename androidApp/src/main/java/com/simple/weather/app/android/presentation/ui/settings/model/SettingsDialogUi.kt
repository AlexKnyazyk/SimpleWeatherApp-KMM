package com.simple.weather.app.android.presentation.ui.settings.model

sealed class SettingsDialogUi {

    object TemperatureUnits : SettingsDialogUi()

    object DistanceUnits : SettingsDialogUi()

    object None : SettingsDialogUi()
}