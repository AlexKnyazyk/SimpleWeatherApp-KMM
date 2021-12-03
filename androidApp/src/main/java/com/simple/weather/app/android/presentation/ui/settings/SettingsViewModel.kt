package com.simple.weather.app.android.presentation.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsDistanceUnitUi
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsTemperatureUnitUi
import com.simple.weather.app.android.presentation.ui.settings.model.SettingsUiState
import com.simple.weather.app.domain.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val uiState = settingsRepository.settingsModelFlow
        .map {
            SettingsUiState(
                tempUnits = if (it.isTempMetric) SettingsTemperatureUnitUi.C else SettingsTemperatureUnitUi.F,
                distanceUnits = if (it.isDistanceMetric) SettingsDistanceUnitUi.KM else SettingsDistanceUnitUi.MILES,
            )
        }.stateIn(viewModelScope, SharingStarted.Lazily, SettingsUiState())

    fun setTempUnits(tempUnits: SettingsTemperatureUnitUi) {
        if (tempUnits != uiState.value.tempUnits) {
            viewModelScope.launch {
                settingsRepository.setTempMetric(tempUnits == SettingsTemperatureUnitUi.C)
            }
        }
    }

    fun setSpeedOrDistanceUnits(distanceUnits: SettingsDistanceUnitUi) {
        if (distanceUnits != uiState.value.distanceUnits) {
            viewModelScope.launch {
                settingsRepository.setDistanceMetric(distanceUnits == SettingsDistanceUnitUi.KM)
            }
        }
    }
}