package com.simple.weather.app.android.presentation.ui.base.weather

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simple.weather.app.android.R
import com.simple.weather.app.android.databinding.FragmentWeatherBinding
import com.simple.weather.app.android.databinding.LayoutCurrentWeatherBinding
import com.simple.weather.app.android.databinding.LayoutCurrentWeatherDetailedBinding
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.model.asData
import com.simple.weather.app.android.presentation.ui.base.BaseListFragment
import com.simple.weather.app.android.presentation.ui.base.weather.forecast.ForecastAdapter
import com.simple.weather.app.android.presentation.ui.base.weather.model.WeatherModelUi
import com.simple.weather.app.android.utils.launchRepeatOnViewLifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseWeatherFragment : BaseListFragment<ForecastAdapter, FragmentWeatherBinding>() {

    protected abstract val viewModel: BaseWeatherViewModel

    override val recyclerView: RecyclerView
        get() = binding.weatherForecastCard.forecastList

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentWeatherBinding.inflate(inflater, container, false)

    override fun createAdapter() = ForecastAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        collectViewModelFlows()
    }

    @CallSuper
    protected open fun initViews() = with(binding) {
        content.setOnRefreshListener {
            viewModel.getWeather(pullToRefresh = true)
        }
        errorLayout.tryAgainButton.setOnClickListener {
            viewModel.getWeather(pullToRefresh = false)
        }
        weatherForecastCard.hourlyToggleButton.setOnClickListener {
            viewModel.setForecastMode(ForecastMode.HOURLY)
        }
        weatherForecastCard.daysToggleButton.setOnClickListener {
            viewModel.setForecastMode(ForecastMode.DAILY)
        }
    }

    @CallSuper
    protected open fun collectViewModelFlows() {
        launchRepeatOnViewLifecycleScope {
            launch {
                viewModel.uiState.collect { bindUiState(it) }
            }
            launch {
                viewModel.forecastMode.collect { bindForecastMode(it) }
            }
        }
    }

    private fun bindUiState(state: UiState<WeatherModelUi>): Unit = with(binding) {
        loadingProgress.isVisible = state is UiState.Loading && !state.pullToRefresh
        content.isRefreshing = state is UiState.Loading && state.pullToRefresh
        content.isVisible = state is UiState.Data || content.isRefreshing
        errorLayout.root.isVisible = state is UiState.Error
        if (state is UiState.Data) {
            currentWeatherCard.bindModel(state.value)
            currentWeatherDetailedCard.bindModel(state.value)
            val mode = viewModel.forecastMode.value
            bindForecast(mode, state.value)
        }
        if (state is UiState.Error) {
            errorLayout.errorMessage.text =
                getString(R.string.error_generic_format, state.uiError.message)
        }
    }

    private fun bindForecastMode(mode: ForecastMode) {
        binding.weatherForecastCard.toggleGroup.check(
            when (mode) {
                ForecastMode.HOURLY -> R.id.hourlyToggleButton
                ForecastMode.DAILY -> R.id.daysToggleButton
            }
        )
        viewModel.uiState.value.asData()?.value?.let { weatherModelUi ->
            bindForecast(mode, weatherModelUi)
        }
    }

    private fun LayoutCurrentWeatherBinding.bindModel(modelUi: WeatherModelUi) {
        locationName.text =
            getString(R.string.location_name_format, modelUi.model.locationName, modelUi.model.locationCountry)
        lastUpdate.text = getString(R.string.last_update_format, modelUi.model.lastUpdated)
        temperatureValue.text = if (modelUi.isTempMetric) {
            getString(R.string.temperature_c_format, modelUi.model.tempC)
        } else {
            getString(R.string.temperature_f_format, modelUi.model.tempF)
        }

        temperatureFeelsValue.text = getString(
            R.string.feels_like_format,
            if (modelUi.isTempMetric) {
                getString(R.string.temperature_c_format, modelUi.model.tempFeelsLikeC)
            } else {
                getString(R.string.temperature_f_format, modelUi.model.tempFeelsLikeF)
            }
        )
        currentWeather.text = modelUi.model.weatherCondition
        Glide.with(requireContext())
            .load(Uri.parse(modelUi.model.weatherConditionIconUrl))
            .into(currentWeatherImage)
    }

    private fun LayoutCurrentWeatherDetailedBinding.bindModel(modelUi: WeatherModelUi) {
        val windSpeed = if (modelUi.isDistanceMetric) {
            getString(R.string.kmh_format, modelUi.model.windKph)
        } else {
            getString(R.string.mph_format, modelUi.model.windMph)
        }
        wind.text = "$windSpeed (${modelUi.model.windDir})"
        humidity.text = getString(R.string.percent_format, modelUi.model.humidity)
        pressure.text = getString(R.string.pressure_mbar_format, modelUi.model.pressureMb)
        visibility.text = if (modelUi.isDistanceMetric) {
            getString(R.string.km_format, modelUi.model.visibilityKm)
        } else {
            getString(R.string.miles_format, modelUi.model.visibilityMiles)
        }
        indexUv.text = modelUi.model.indexUv.toString()
    }

    private fun bindForecast(mode: ForecastMode, modelUi: WeatherModelUi) {
        adapter.isTempMetric = modelUi.isTempMetric
        adapter.isDistanceMetric = modelUi.isDistanceMetric
        adapter.itemModels = when (mode) {
            ForecastMode.HOURLY -> modelUi.model.forecastHourly
            ForecastMode.DAILY -> modelUi.model.forecastDaily
        }
    }
}