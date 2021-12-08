package com.simple.weather.app.android.presentation.ui.base.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.weather.model.CurrentWeatherUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.DetailedWeatherUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.ForecastWeatherUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.SettingsUnitsUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.WeatherUi
import com.simple.weather.app.data.model.request.WeatherRequest
import com.simple.weather.app.domain.domain.model.SettingsUnitsModel
import com.simple.weather.app.domain.domain.model.WeatherModel
import com.simple.weather.app.domain.domain.repository.SettingsRepository
import com.simple.weather.app.domain.domain.usecase.weather.IGetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseWeatherViewModel(
    private val getWeatherUseCase: IGetWeatherUseCase,
    settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<UiState<WeatherUi>>(UiState.loading(pullToRefresh = false))
    val uiState = _uiState.asStateFlow()

    private val _forecastMode = MutableStateFlow(ForecastMode.HOURLY)
    val forecastMode = _forecastMode.asStateFlow()

    private val settingsModelState = settingsRepository.settingsUnitsModelFlow
        .onEach { settings ->
            (uiState.value as? UiState.Data)?.value?.let { weatherUi ->
                _uiState.emit(
                    UiState.data(
                        weatherUi.copy(
                            settingsUnits = SettingsUnitsUi(
                                isTempMetric = settings.isTempMetric,
                                isDistanceMetric = settings.isDistanceMetric,
                            )
                        )
                    )
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, SettingsUnitsModel())

    fun setForecastMode(mode: ForecastMode) {
        val uiState = _uiState.value
        if (_forecastMode.value != mode && uiState is UiState.Data) {
            _forecastMode.value = mode
        }
    }

    protected fun getWeather(pullToRefresh: Boolean, weatherRequest: WeatherRequest) =
        viewModelScope.launch {
            _uiState.value = UiState.loading(pullToRefresh)
            _uiState.value = getWeatherUseCase(weatherRequest).fold(
                onSuccess = { getWeatherModelUiState(it) },
                onFailure = { UiState.error(it) }
            )
            _forecastMode.value = _forecastMode.value
        }

    abstract fun getWeather(pullToRefresh: Boolean)

    private fun getWeatherModelUiState(
        weather: WeatherModel,
        settings: SettingsUnitsModel = settingsModelState.value
    ): UiState<WeatherUi> {
        return UiState.data(
            WeatherUi(
                currentWeather = CurrentWeatherUi(
                    locationName = weather.locationName,
                    locationCountry = weather.locationCountry,
                    lastUpdated = weather.lastUpdated,
                    tempC = weather.tempC,
                    tempF = weather.tempF,
                    tempFeelsLikeC = weather.tempFeelsLikeC,
                    tempFeelsLikeF = weather.tempFeelsLikeF,
                    weatherConditionIconUrl = weather.weatherConditionIconUrl,
                    weatherCondition = weather.weatherCondition
                ),
                forecastWeather = ForecastWeatherUi(
                    forecastDaily = weather.forecastDaily,
                    forecastHourly = weather.forecastHourly
                ),
                detailedWeather = DetailedWeatherUi(
                    windKph = weather.windKph,
                    windMph = weather.windMph,
                    windDir = weather.windDir,
                    humidity = weather.humidity,
                    pressureMb = weather.pressureMb,
                    visibilityKm = weather.visibilityKm,
                    visibilityMiles = weather.visibilityMiles,
                    indexUv = weather.indexUv
                ),
                settingsUnits = SettingsUnitsUi(
                    isTempMetric = settings.isTempMetric,
                    isDistanceMetric = settings.isDistanceMetric
                )
            )
        )
    }
}