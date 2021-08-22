package com.simple.weather.app.android.presentation.ui.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.data.model.CurrentWeatherData
import com.simple.weather.app.android.databinding.FragmentHomeBinding
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import org.kodein.di.instance

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModelFactory: HomeViewModel.Factory by instance()
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(viewLifecycleOwner, ::bindUiState)
        binding.root.setOnRefreshListener {
            viewModel.getWeather()
        }
    }

    private fun bindUiState(state: UiState<CurrentWeatherData>?) = with(binding) {
        state ?: return
        root.isRefreshing = state is UiState.Loading
        if (state is UiState.Data) {
            val location = state.value.name
            locationName.text =
                getString(R.string.location_name_format, location.name, location.country)
            val weather = state.value.current
            lastUpdate.text = getString(R.string.last_update_format, weather.lastUpdated)
            temperatureValue.text = getString(R.string.temperature_c_format, weather.tempC.toInt())
            currentWeather.text = weather.condition.text
            Glide.with(requireContext())
                .load(Uri.parse("https:${weather.condition.icon}"))
                .into(currentWeatherImage)
        }
    }
}