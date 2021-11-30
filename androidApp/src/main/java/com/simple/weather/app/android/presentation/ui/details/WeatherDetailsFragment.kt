package com.simple.weather.app.android.presentation.ui.details

import com.simple.weather.app.android.presentation.ui.base.BaseWeatherFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WeatherDetailsFragment : BaseWeatherFragment() {

    override val viewModel: WeatherDetailsViewModel by viewModel {
        parametersOf(requireArguments().getString(ARG_KEY_NAME).orEmpty())
    }

    companion object {
        const val ARG_KEY_NAME = "KEY_NAME"
    }
}