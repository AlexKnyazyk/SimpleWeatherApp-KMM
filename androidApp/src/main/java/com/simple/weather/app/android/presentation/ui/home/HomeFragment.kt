package com.simple.weather.app.android.presentation.ui.home

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import com.simple.weather.app.android.presentation.ui.base.BaseWeatherFragment
import com.simple.weather.app.android.utils.launchRepeatOnViewLifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseWeatherFragment() {

    override val viewModel: HomeViewModel by viewModel()

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        viewModel.getWeather(pullToRefresh = false)
    }

    override fun collectViewModelFlows() {
        super.collectViewModelFlows()
        launchRepeatOnViewLifecycleScope {
            launch {
                viewModel.locationPermissionsEvent.collect {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
                }
            }
        }
    }
}