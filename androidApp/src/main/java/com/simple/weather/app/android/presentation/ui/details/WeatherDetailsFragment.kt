package com.simple.weather.app.android.presentation.ui.details

import com.simple.weather.app.android.presentation.ui.base.BaseWeatherFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WeatherDetailsFragment : BaseWeatherFragment() {

    override val viewModel: WeatherDetailsViewModel by viewModel {
        parametersOf(requireArguments().getInt(ARG_KEY_ID))
    }

    companion object {
        const val ARG_KEY_ID = "id"
    }
}