package com.simple.weather.app.android.presentation.ui.home

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentHomeBinding
import com.simple.weather.app.android.databinding.LayoutCurrentWeatherBinding
import com.simple.weather.app.android.databinding.LayoutCurrentWeatherDetailedBinding
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import com.simple.weather.app.android.presentation.ui.home.forecast.ForecastAdapter
import org.kodein.di.instance

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModelFactory: HomeViewModel.Factory by instance()
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            viewModel.getWeather(pullToRefresh = false)
        }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        initViews()
    }

    private fun observeLiveData() {
        viewModel.uiState.observe(viewLifecycleOwner, ::bindUiState)
        viewModel.locationPermissionsEvent.observe(viewLifecycleOwner) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        viewModel.forecastMode.observe(viewLifecycleOwner) { mode ->
            binding.weatherForecastCard.toggleGroup.check(
                when (mode!!) {
                    ForecastMode.HOURLY -> R.id.hourlyToggleButton
                    ForecastMode.DAILY -> R.id.daysToggleButton
                }
            )
            val weatherModel = (viewModel.uiState.value as? UiState.Data)?.value ?: return@observe
            bindForecast(mode, weatherModel)
        }
    }

    private fun initViews() = with(binding) {
        content.setOnRefreshListener {
            viewModel.getWeather(pullToRefresh = true)
        }
        errorLayout.tryAgainButton.setOnClickListener {
            viewModel.getWeather(pullToRefresh = false)
        }
        weatherForecastCard.forecastList.apply {
            adapter = ForecastAdapter()
        }
        weatherForecastCard.hourlyToggleButton.setOnClickListener {
            viewModel.setForecastMode(ForecastMode.HOURLY)
        }
        weatherForecastCard.daysToggleButton.setOnClickListener {
            viewModel.setForecastMode(ForecastMode.DAILY)
        }
    }

    private fun bindUiState(state: UiState<WeatherModel>?) = with(binding) {
        state ?: return
        loadingProgress.isVisible = state is UiState.Loading && !state.pullToRefresh
        content.isRefreshing = state is UiState.Loading && state.pullToRefresh
        content.isVisible = state is UiState.Data || content.isRefreshing
        errorLayout.root.isVisible = state is UiState.Error
        if (state is UiState.Data) {
            currentWeatherCard.bindModel(state.value)
            currentWeatherDetailedCard.bindModel(state.value)
            val mode = viewModel.forecastMode.value
            bindForecast(mode!!, state.value)
        }
        if (state is UiState.Error) {
            errorLayout.errorMessage.text =
                getString(R.string.error_generic_format, state.uiError.message)
        }
    }

    private fun LayoutCurrentWeatherBinding.bindModel(model: WeatherModel) {
        locationName.text =
            getString(R.string.location_name_format, model.locationName, model.locationCountry)
        lastUpdate.text = getString(R.string.last_update_format, model.lastUpdated)
        temperatureValue.text =
            getString(R.string.temperature_c_format, model.tempC)
        temperatureFeelsValue.text = getString(
            R.string.feels_like_format,
            getString(R.string.temperature_c_format, model.tempFeelsLikeC)
        )
        currentWeather.text = model.weatherCondition
        Glide.with(requireContext())
            .load(Uri.parse(model.weatherConditionIconUrl))
            .into(currentWeatherImage)
    }

    private fun LayoutCurrentWeatherDetailedBinding.bindModel(model: WeatherModel) {
        wind.text =
            "${getString(R.string.kmh_format, model.windKph)} (${model.windDir})"
        humidity.text = getString(R.string.percent_format, model.humidity)
        pressure.text = getString(R.string.pressure_mbar_format, model.pressureMb)
        visibility.text = getString(R.string.km_format, model.visibilityKm)
        indexUv.text = model.indexUv.toString()
    }

    private fun bindForecast(mode: ForecastMode, weatherModel: WeatherModel) {
        (binding.weatherForecastCard.forecastList.adapter as ForecastAdapter).itemModels =
            when (mode) {
                ForecastMode.HOURLY -> weatherModel.forecastHourly
                ForecastMode.DAILY -> weatherModel.forecastDaily
            }
    }
}