package com.simple.weather.app.android.presentation.ui.base.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.presentation.model.ForecastModeUi
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.model.asData
import com.simple.weather.app.android.presentation.ui.base.weather.model.SettingsUnitsUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.WeatherModelUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.toUi
import com.simple.weather.app.data.model.request.WeatherRequest
import com.simple.weather.app.domain.domain.repository.SettingsRepository
import com.simple.weather.app.domain.domain.usecase.weather.IGetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseWeatherViewModel(
    private val getWeatherUseCase: IGetWeatherUseCase,
    settingsRepository: SettingsRepository
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _uiState = MutableStateFlow<UiState<WeatherModelUi>>(UiState.loading())
    val uiState = _uiState.asStateFlow()

    private val _forecastMode = MutableStateFlow(ForecastModeUi.HOURLY)
    val forecastMode = _forecastMode.asStateFlow()

    private val settingsUnitsState = settingsRepository.settingsUnitsModelFlow
        .map { it.toUi() }
        .onEach { settings -> updateUiStateWithSettings(settings) }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SettingsUnitsUi())

    fun setForecastMode(mode: ForecastModeUi) {
        val uiState = _uiState.value
        if (_forecastMode.value != mode && uiState is UiState.Data) {
            _forecastMode.value = mode
        }
    }

    protected fun getWeather(pullToRefresh: Boolean, weatherRequest: WeatherRequest) {
        viewModelScope.launch {
            if (pullToRefresh) {
                _isRefreshing.value = true
            } else {
                _uiState.value = UiState.loading()
            }
            _uiState.value = getWeatherUseCase(weatherRequest).fold(
                onSuccess = { weatherModel ->
                    UiState.data(weatherModel.toUi(settingsUnitsState.value))
                },
                onFailure = { UiState.error(it) }
            )
            _isRefreshing.value = false
        }
    }

    private suspend fun updateUiStateWithSettings(settingsUnits: SettingsUnitsUi) {
        uiState.value.asData()?.value?.let { weatherUi ->
            _uiState.emit(
                UiState.data(weatherUi.copy(settingsUnits = settingsUnits))
            )
        }
    }

    abstract fun getWeather(pullToRefresh: Boolean)
}