package com.simple.weather.app.android.presentation.ui.details

import com.simple.weather.app.android.presentation.ui.base.weather.BaseWeatherViewModel
import com.simple.weather.app.data.model.request.WeatherRequest
import com.simple.weather.app.domain.domain.repository.SettingsRepository
import com.simple.weather.app.domain.domain.usecase.weather.IGetWeatherUseCase

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