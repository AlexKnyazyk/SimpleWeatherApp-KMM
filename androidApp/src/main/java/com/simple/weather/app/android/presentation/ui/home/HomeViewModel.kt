package com.simple.weather.app.android.presentation.ui.home

import androidx.lifecycle.*
import com.simple.weather.app.android.data.model.LocationResult
import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.data.repository.location.LocationRepository
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.domain.usecase.GetWeatherUseCase
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val locationRepository: LocationRepository,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _uiState =
        MutableLiveData<UiState<WeatherModel>>(UiState.loading(pullToRefresh = false))
    val uiState: LiveData<UiState<WeatherModel>> = _uiState

    private val _locationPermissionsEvent = MutableLiveData<Unit>()
    val locationPermissionsEvent: LiveData<Unit> = _locationPermissionsEvent

    private val _forecastMode = MutableLiveData(ForecastMode.HOURLY)
    val forecastMode: LiveData<ForecastMode> = _forecastMode

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
                    _locationPermissionsEvent.value = Unit
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
        private val getWeatherUseCase: GetWeatherUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(locationRepository, getWeatherUseCase) as T
        }
    }
}