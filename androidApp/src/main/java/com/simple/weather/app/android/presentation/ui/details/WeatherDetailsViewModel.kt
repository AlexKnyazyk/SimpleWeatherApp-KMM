package com.simple.weather.app.android.presentation.ui.details

import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.domain.repository.SettingsRepository
import com.simple.weather.app.android.domain.usecase.weather.IGetWeatherUseCase
import com.simple.weather.app.android.presentation.ui.base.weather.BaseWeatherViewModel

class WeatherDetailsViewModel(
    private val id: Int,
    getWeatherUseCase: IGetWeatherUseCase,
    settingsRepository: SettingsRepository
) : BaseWeatherViewModel(getWeatherUseCase, settingsRepository) {

    init {
        getWeather(pullToRefresh = false)
    }

    override fun getWeather(pullToRefresh: Boolean) {
        getWeather(pullToRefresh, WeatherRequest.FavoriteId(id))
    }
}