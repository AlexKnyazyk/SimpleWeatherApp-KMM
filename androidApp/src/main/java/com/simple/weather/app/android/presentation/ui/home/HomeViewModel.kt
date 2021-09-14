package com.simple.weather.app.android.presentation.ui.home

import androidx.lifecycle.*
import com.simple.weather.app.android.data.model.WeatherData
import com.simple.weather.app.android.data.repository.weather.WeatherRepository
import com.simple.weather.app.android.domain.model.ForecastItem
import com.simple.weather.app.android.domain.model.ForecastItemMode
import com.simple.weather.app.android.presentation.model.UiState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<WeatherData>>()
    val uiState: LiveData<UiState<WeatherData>> = _uiState

    private val _forecastMode = MutableLiveData(ForecastItemMode.HOURLY)
    val forecastMode: LiveData<ForecastItemMode> = _forecastMode

    val forecastData: LiveData<List<ForecastItem>> = _forecastMode.map { mode ->
        val weather = (uiState.value as? UiState.Data)?.value ?: return@map emptyList()
        when (mode!!) {
            ForecastItemMode.HOURLY -> {
                weather.forecast.forecastday.firstOrNull()?.hour?.map {
                    ForecastItem(
                        date = it.timeEpoch,
                        mode = mode,
                        temperature = it.tempC,
                        iconUrl = it.condition.icon,
                        windSpeed = it.windKph,
                        windDir = it.windDir
                    )
                }
            }
            ForecastItemMode.DAILY -> {
                weather.forecast.forecastday.map {
                    ForecastItem(
                        date = it.dateEpoch,
                        mode = mode,
                        temperature = it.day.avgtempC,
                        temperatureMax = it.day.maxtempC,
                        temperatureMin = it.day.mintempC,
                        iconUrl = it.day.condition.icon,
                        windSpeed = it.day.maxwindKph,
                        windDir = ""
                    )
                }
            }
        }.orEmpty()
    }

    init {
        getWeather(pullToRefresh = false)
    }

    fun getWeather(pullToRefresh: Boolean) = viewModelScope.launch {
        _uiState.value = UiState.loading(pullToRefresh)
        _uiState.value = weatherRepository.getCurrentWeather().fold(
            onSuccess = { UiState.data(it) },
            onFailure = { UiState.error(it) }
        )
        _forecastMode.value = _forecastMode.value
    }

    fun setForecastMode(mode: ForecastItemMode) {
        val uiState = _uiState.value
        if (_forecastMode.value != mode && uiState is UiState.Data) {
            _forecastMode.value = mode
        }
    }

    class Factory(private val weatherRepository: WeatherRepository) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(weatherRepository) as T
        }
    }
}