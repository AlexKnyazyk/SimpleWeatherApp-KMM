package com.simple.weather.app.android.presentation.ui.home

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.simple.weather.app.android.presentation.ui.base.weather.BaseWeatherScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeWeatherScreen() {
    val viewModel = getViewModel<HomeViewModel>()

    CollectLocationPermission(viewModel)

    BaseWeatherScreen(viewModel = viewModel)
}

@Composable
private fun CollectLocationPermission(
    viewModel: HomeViewModel
) {
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        viewModel.getWeather(pullToRefresh = false)
    }

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.locationPermissionsEvent.collect {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }
}