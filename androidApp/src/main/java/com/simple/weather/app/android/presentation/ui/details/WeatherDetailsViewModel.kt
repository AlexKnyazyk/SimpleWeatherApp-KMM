package com.simple.weather.app.android.presentation.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.simple.weather.app.android.data.model.request.WeatherRequest
import com.simple.weather.app.android.domain.usecase.IGetWeatherUseCase
import com.simple.weather.app.android.presentation.ui.base.BaseWeatherViewModel

class WeatherDetailsViewModel(
    private val name: String,
    getWeatherUseCase: IGetWeatherUseCase
) : BaseWeatherViewModel(getWeatherUseCase) {

    init {
        getWeather(pullToRefresh = false)
    }

    override fun getWeather(pullToRefresh: Boolean) {
        getWeather(pullToRefresh, WeatherRequest.Name(name))
    }

    class Factory(
        private val getWeatherUseCase: IGetWeatherUseCase
    ) {
        @Suppress("UNCHECKED_CAST")
        fun create(name: String) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WeatherDetailsViewModel(name, getWeatherUseCase) as T
            }
        }
    }
}