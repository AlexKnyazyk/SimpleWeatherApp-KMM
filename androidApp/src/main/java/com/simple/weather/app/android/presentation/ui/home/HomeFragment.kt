package com.simple.weather.app.android.presentation.ui.home

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.data.model.WeatherData
import com.simple.weather.app.android.databinding.FragmentHomeBinding
import com.simple.weather.app.android.databinding.LayoutCurrentWeatherBinding
import com.simple.weather.app.android.databinding.LayoutCurrentWeatherDetailedBinding
import com.simple.weather.app.android.domain.model.ForecastItemMode
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.BaseFragment
import com.simple.weather.app.android.presentation.ui.home.forecast.ForecastAdapter
import org.kodein.di.instance
import kotlin.math.round

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModelFactory: HomeViewModel.Factory by instance()
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
        initViews()
    }

    private fun observeLiveData() {
        viewModel.uiState.observe(viewLifecycleOwner, ::bindUiState)
        viewModel.forecastMode.observe(viewLifecycleOwner) { mode ->
            binding.weatherForecastCard.toggleGroup.check(
                when (mode!!) {
                    ForecastItemMode.HOURLY -> R.id.hourlyToggleButton
                    ForecastItemMode.DAILY -> R.id.daysToggleButton
                }
            )
        }
        viewModel.forecastData.observe(viewLifecycleOwner) {
            (binding.weatherForecastCard.forecastList.adapter as ForecastAdapter).submitList(it)
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
            viewModel.setForecastMode(ForecastItemMode.HOURLY)
        }
        weatherForecastCard.daysToggleButton.setOnClickListener {
            viewModel.setForecastMode(ForecastItemMode.DAILY)
        }
    }

    private fun bindUiState(state: UiState<WeatherData>?) = with(binding) {
        state ?: return
        loadingProgress.isVisible = state is UiState.Loading && !state.pullToRefresh
        content.isRefreshing = state is UiState.Loading && state.pullToRefresh
        content.isVisible = state is UiState.Data || content.isRefreshing
        errorLayout.root.isVisible = state is UiState.Error
        if (state is UiState.Data) {
            currentWeatherCard.bindData(state.value)
            currentWeatherDetailedCard.bindData(state.value)
        }
        if (state is UiState.Error) {
            errorLayout.errorMessage.text =
                getString(R.string.error_generic_format, state.uiError.message)
        }
    }

    private fun LayoutCurrentWeatherBinding.bindData(data: WeatherData) {
        val location = data.name
        locationName.text =
            getString(R.string.location_name_format, location.name, location.country)
        val weather = data.current
        lastUpdate.text = getString(R.string.last_update_format, weather.lastUpdated)
        temperatureValue.text =
            getString(R.string.temperature_c_format, round(weather.tempC).toInt())
        temperatureFeelsValue.text = getString(
            R.string.feels_like_format,
            getString(R.string.temperature_c_format, round(weather.feelslikeC).toInt())
        )
        currentWeather.text = weather.condition.text
        Glide.with(requireContext())
            .load(Uri.parse("https:${weather.condition.icon}"))
            .into(currentWeatherImage)
    }

    private fun LayoutCurrentWeatherDetailedBinding.bindData(data: WeatherData) {
        wind.text =
            "${getString(R.string.kmh_format, data.current.windKph)} (${data.current.windDir})"
        humidity.text = getString(R.string.percent_format, data.current.humidity)
        pressure.text = getString(R.string.pressure_mbar_format, data.current.pressureMb)
        visibility.text = getString(R.string.km_format, data.current.visKm)
        indexUv.text = data.current.uv.toInt().toString()
    }
}