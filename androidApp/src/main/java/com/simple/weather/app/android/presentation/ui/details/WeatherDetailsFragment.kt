package com.simple.weather.app.android.presentation.ui.details

import android.os.Bundle
import com.google.android.material.transition.MaterialSharedAxis
import com.simple.weather.app.android.presentation.ui.base.weather.BaseWeatherFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class WeatherDetailsFragment : BaseWeatherFragment() {

    override val viewModel: WeatherDetailsViewModel by viewModel {
        parametersOf(requireArguments().getInt(ARG_KEY_ID))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
        returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
    }

    companion object {
        const val ARG_KEY_ID = "id"
    }
}