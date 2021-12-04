package com.simple.weather.app.android.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.presentation.ui.base.weather.BaseWeatherViewModel
import com.simple.weather.app.data.model.LocationResult
import com.simple.weather.app.data.model.request.WeatherRequest
import com.simple.weather.app.domain.repository.DeviceLocationRepository
import com.simple.weather.app.domain.repository.SettingsRepository
import com.simple.weather.app.domain.usecase.weather.IGetWeatherUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class HomeViewModel(
    private val deviceLocationRepository: DeviceLocationRepository,
    getWeatherUseCase: IGetWeatherUseCase,
    settingsRepository: SettingsRepository
) : BaseWeatherViewModel(getWeatherUseCase, settingsRepository) {

    private val _locationPermissionsEvent = MutableSharedFlow<Unit?>(replay = 1, extraBufferCapacity = 1)
    val locationPermissionsEvent = _locationPermissionsEvent.asSharedFlow().filterNotNull()

    private var locationPermissionAsked: Boolean = false

    init {
        getWeather(pullToRefresh = false)
    }

    override fun getWeather(pullToRefresh: Boolean) {
        viewModelScope.launch {
            _locationPermissionsEvent.emit(null)
        }
        when (val result = deviceLocationRepository.getLocation()) {
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
        getWeather(pullToRefresh, WeatherRequest.AutoIPAddress)
    }
}