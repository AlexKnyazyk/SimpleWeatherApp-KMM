package com.simple.weather.app.android.presentation.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.domain.usecase.weather.IGetWeatherUseCase
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseWeatherViewModel(
    private val getWeatherUseCase: IGetWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<WeatherModel>>(UiState.loading(pullToRefresh = false))
    val uiState = _uiState.asStateFlow()

    private val _forecastMode = MutableStateFlow(ForecastMode.HOURLY)
    val forecastMode = _forecastMode.asStateFlow()

    fun setForecastMode(mode: ForecastMode) {
        val uiState = _uiState.value
        if (_forecastMode.value != mode && uiState is UiState.Data) {
            _forecastMode.value = mode
        }
    }

    protected fun getWeather(pullToRefresh: Boolean, weatherRequest: WeatherRequest) = viewModelScope.launch {
        _uiState.value = UiState.loading(pullToRefresh)
        _uiState.value = getWeatherUseCase(weatherRequest).fold(
            onSuccess = { UiState.data(it) },
            onFailure = { UiState.error(it) }
        )
        _forecastMode.value = _forecastMode.value
    }

    abstract fun getWeather(pullToRefresh: Boolean)
}