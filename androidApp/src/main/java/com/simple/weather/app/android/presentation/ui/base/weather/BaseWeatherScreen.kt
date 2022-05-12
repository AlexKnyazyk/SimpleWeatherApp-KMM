package com.simple.weather.app.android.presentation.ui.base.weather

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.simple.weather.app.android.presentation.model.ForecastModeUi
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.weather.forecast.ForecastWeatherCard
import com.simple.weather.app.android.presentation.ui.base.weather.model.WeatherModelUi
import com.simple.weather.app.android.presentation.ui.error.ErrorContentWithTryAgain

@Composable
fun BaseWeatherScreen(viewModel: BaseWeatherViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val forecastMode by viewModel.forecastMode.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.getWeather(pullToRefresh = true) },
    ) {
        when (uiState) {
            is UiState.Data -> {
                val weatherModel = (uiState as UiState.Data<WeatherModelUi>).value
                WeatherContent(
                    weatherModel,
                    forecastMode,
                    onModeSelected = { viewModel.setForecastMode(it) }
                )
            }
            is UiState.Error -> {
                val error = (uiState as UiState.Error<WeatherModelUi>).error
                ErrorContentWithTryAgain(error, tryAgainAction = {
                    viewModel.getWeather(pullToRefresh = false)
                })
            }
            is UiState.Loading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun WeatherContent(
    model: WeatherModelUi,
    forecastMode: ForecastModeUi,
    onModeSelected: (ForecastModeUi) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(all = 16.dp),
    ) {
        item {
            CurrentWeatherCard(model.currentWeather, model.settingsUnits)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            ForecastWeatherCard(
                model.forecastWeather,
                model.settingsUnits,
                forecastMode,
                onModeSelected
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            DetailedWeatherCard(model.detailedWeather, model.settingsUnits)
        }
    }
}