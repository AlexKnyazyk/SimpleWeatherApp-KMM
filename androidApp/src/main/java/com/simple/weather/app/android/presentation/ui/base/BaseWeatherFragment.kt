package com.simple.weather.app.android.presentation.ui.base

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
import com.simple.weather.app.android.domain.model.WeatherModel
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.model.asData
import com.simple.weather.app.android.presentation.ui.base.forecast.ForecastAdapter
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

    private fun bindUiState(state: UiState<WeatherModel>): Unit = with(binding) {
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
        viewModel.uiState.value.asData()?.value?.let { weatherModel ->
            bindForecast(mode, weatherModel)
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
        adapter.itemModels = when (mode) {
            ForecastMode.HOURLY -> weatherModel.forecastHourly
            ForecastMode.DAILY -> weatherModel.forecastDaily
        }
    }
}