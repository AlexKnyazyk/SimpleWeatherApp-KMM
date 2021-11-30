package com.simple.weather.app.android.presentation.ui.details

import androidx.fragment.app.viewModels
import com.simple.weather.app.android.presentation.ui.base.BaseWeatherFragment
import org.kodein.di.provider

class WeatherDetailsFragment : BaseWeatherFragment() {

    private val viewModelFactory by provider<WeatherDetailsViewModel.Factory>()
    override val viewModel: WeatherDetailsViewModel by viewModels {
        viewModelFactory().create(requireArguments().getString(ARG_KEY_NAME).orEmpty())
    }

    companion object {
        const val ARG_KEY_NAME = "KEY_NAME"
    }
}