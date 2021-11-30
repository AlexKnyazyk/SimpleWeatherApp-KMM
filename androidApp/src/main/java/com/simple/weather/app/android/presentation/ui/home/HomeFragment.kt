package com.simple.weather.app.android.presentation.ui.home

import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.simple.weather.app.android.presentation.ui.base.BaseWeatherFragment
import com.simple.weather.app.android.utils.launchRepeatOnViewLifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.kodein.di.provider

class HomeFragment : BaseWeatherFragment() {

    private val viewModelFactory by provider<HomeViewModel.Factory>()
    override val viewModel: HomeViewModel by viewModels { viewModelFactory() }

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