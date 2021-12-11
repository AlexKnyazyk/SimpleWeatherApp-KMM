package com.simple.weather.app.android.presentation.ui.details

import androidx.compose.runtime.Composable
import com.simple.weather.app.android.presentation.ui.base.weather.BaseWeatherScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun LocationWeatherScreen(
    locationId: Int
) {
    val viewModel = getViewModel<LocationWeatherViewModel>(
        parameters = { parametersOf(locationId) }
    )
    BaseWeatherScreen(viewModel)
}