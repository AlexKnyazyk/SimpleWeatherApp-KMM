package com.simple.weather.app.android.presentation.ui.home

import androidx.lifecycle.*
import com.simple.weather.app.android.data.model.CurrentWeatherData
import com.simple.weather.app.android.data.repository.weather.WeatherRepository
import com.simple.weather.app.android.presentation.model.UiState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<CurrentWeatherData>>()
    val uiState: LiveData<UiState<CurrentWeatherData>> = _uiState

    init {
        getWeather()
    }

    fun getWeather() = viewModelScope.launch {
        _uiState.value = UiState.loading()
        _uiState.value = weatherRepository.getCurrentWeather().fold(
            onSuccess = { UiState.data(it) },
            onFailure = { UiState.error(it) }
        )
    }

    class Factory(private val weatherRepository: WeatherRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(weatherRepository) as T
        }
    }
}