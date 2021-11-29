package com.simple.weather.app.android.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.data.model.LocationResult
import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.domain.repository.LocationRepository
import com.simple.weather.app.android.domain.usecase.IGetWeatherUseCase
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val locationRepository: LocationRepository,
    private val getWeatherUseCase: IGetWeatherUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<WeatherModel>>(UiState.loading(pullToRefresh = false))
    val uiState = _uiState.asStateFlow()

    private val _locationPermissionsEvent = MutableSharedFlow<Unit>()
    val locationPermissionsEvent = _locationPermissionsEvent.asSharedFlow()

    private val _forecastMode = MutableStateFlow(ForecastMode.HOURLY)
    val forecastMode = _forecastMode.asStateFlow()

    private var locationPermissionAsked: Boolean = false

    init {
        getWeather(pullToRefresh = false)
    }

    fun getWeather(pullToRefresh: Boolean) {
        when (val result = locationRepository.getLocation()) {
            is LocationResult.Success ->
                getWeather(pullToRefresh, WeatherRequest.Location(result.lat, result.lon))
            LocationResult.NoLocation -> getWeatherWithoutLocation(pullToRefresh)
            LocationResult.NoPermission -> {
                if (locationPermissionAsked) {
                    getWeatherWithoutLocation(pullToRefresh)
                } else {
                    locationPermissionAsked = true
                    viewModelScope.launch {
                        _locationPermissionsEvent.emit(Unit)
                    }
                }
            }
        }
    }

    private fun getWeatherWithoutLocation(pullToRefresh: Boolean) {
        //TODO
    }

    private fun getWeather(pullToRefresh: Boolean, weatherRequest: WeatherRequest) =
        viewModelScope.launch {
            _uiState.value = UiState.loading(pullToRefresh)
            _uiState.value = getWeatherUseCase(weatherRequest).fold(
                onSuccess = { UiState.data(it) },
                onFailure = { UiState.error(it) }
            )
            _forecastMode.value = _forecastMode.value
        }

    fun setForecastMode(mode: ForecastMode) {
        val uiState = _uiState.value
        if (_forecastMode.value != mode && uiState is UiState.Data) {
            _forecastMode.value = mode
        }
    }

    class Factory(
        private val locationRepository: LocationRepository,
        private val getWeatherUseCase: IGetWeatherUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(locationRepository, getWeatherUseCase) as T
        }
    }
}