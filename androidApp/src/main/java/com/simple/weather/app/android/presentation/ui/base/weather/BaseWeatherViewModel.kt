package com.simple.weather.app.android.presentation.ui.base.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.weather.model.WeatherModelUi
import com.simple.weather.app.data.model.request.WeatherRequest
import com.simple.weather.app.domain.model.SettingsUnitsModel
import com.simple.weather.app.domain.model.WeatherModel
import com.simple.weather.app.domain.repository.SettingsRepository
import com.simple.weather.app.domain.usecase.weather.IGetWeatherUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseWeatherViewModel(
    private val getWeatherUseCase: IGetWeatherUseCase,
    settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<WeatherModelUi>>(UiState.loading(pullToRefresh = false))
    val uiState = _uiState.asStateFlow()

    private val _forecastMode = MutableStateFlow(ForecastMode.HOURLY)
    val forecastMode = _forecastMode.asStateFlow()

    private val settingsModelState = settingsRepository.settingsUnitsModelFlow
        .onEach { settings ->
            (uiState.value as? UiState.Data)?.value?.let { weatherModelUi ->
                _uiState.emit(getWeatherModelUiState(weatherModelUi.model, settings))
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SettingsUnitsModel())

    fun setForecastMode(mode: ForecastMode) {
        val uiState = _uiState.value
        if (_forecastMode.value != mode && uiState is UiState.Data) {
            _forecastMode.value = mode
        }
    }

    protected fun getWeather(pullToRefresh: Boolean, weatherRequest: WeatherRequest) = viewModelScope.launch {
        _uiState.value = UiState.loading(pullToRefresh)
        _uiState.value = getWeatherUseCase(weatherRequest).fold(
            onSuccess = { getWeatherModelUiState(it) },
            onFailure = { UiState.error(it) }
        )
        _forecastMode.value = _forecastMode.value
    }

    abstract fun getWeather(pullToRefresh: Boolean)

    private fun getWeatherModelUiState(weather: WeatherModel, settings: SettingsUnitsModel = settingsModelState.value) = with(settings) {
        UiState.data(WeatherModelUi(weather, isTempMetric, isDistanceMetric))
    }
}