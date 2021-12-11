package com.simple.weather.app.android.presentation.ui.base.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.ErrorContentWithTryAgain
import com.simple.weather.app.android.presentation.ui.base.weather.forecast.ForecastWeatherCard
import com.simple.weather.app.android.presentation.ui.base.weather.model.WeatherUi

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
                val weatherModel = (uiState as UiState.Data<WeatherUi>).value
                WeatherContent(
                    weatherModel,
                    forecastMode,
                    onModeSelected = { viewModel.setForecastMode(it) }
                )
            }
            is UiState.Error -> {
                val error = (uiState as UiState.Error<WeatherUi>).error
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
fun WeatherContent(model: WeatherUi, forecastMode: ForecastMode, onModeSelected: (ForecastMode) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(all = 16.dp),
    ) {
        itemsIndexed(WeatherCard.cards) { index, card ->
            when (card) {
                WeatherCard.CURRENT -> {
                    CurrentWeatherCard(model.currentWeather, model.settingsUnits)
                }
                WeatherCard.FORECAST -> {
                    ForecastWeatherCard(
                        model.forecastWeather,
                        model.settingsUnits,
                        forecastMode,
                        onModeSelected
                    )
                }
                WeatherCard.DETAILED -> {
                    DetailedWeatherCard(model.detailedWeather, model.settingsUnits)
                }
            }
            if (index != WeatherCard.cards.lastIndex) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

private enum class WeatherCard {
    CURRENT,
    FORECAST,
    DETAILED;

    companion object {
        val cards = values()
    }
}