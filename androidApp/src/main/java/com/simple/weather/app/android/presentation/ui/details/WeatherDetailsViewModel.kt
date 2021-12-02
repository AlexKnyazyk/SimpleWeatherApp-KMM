package com.simple.weather.app.android.presentation.ui.details

import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.domain.usecase.weather.IGetWeatherUseCase
import com.simple.weather.app.android.presentation.ui.base.BaseWeatherViewModel

class WeatherDetailsViewModel(
    private val id: Int,
    getWeatherUseCase: IGetWeatherUseCase
) : BaseWeatherViewModel(getWeatherUseCase) {

    init {
        getWeather(pullToRefresh = false)
    }

    override fun getWeather(pullToRefresh: Boolean) {
        getWeather(pullToRefresh, WeatherRequest.FavoriteId(id))
    }
}