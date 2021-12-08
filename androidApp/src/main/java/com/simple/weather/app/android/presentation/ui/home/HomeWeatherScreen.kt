package com.simple.weather.app.android.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.simple.weather.app.android.presentation.ui.base.weather.model.WeatherUi
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeWeatherScreen() {
    val viewModel = getViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(uiState is UiState.Loading && (uiState as UiState.Loading<WeatherUi>).pullToRefresh),
        onRefresh = { viewModel.getWeather(pullToRefresh = true) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            when (uiState) {
                is UiState.Data -> {
                    val weatherModel = (uiState as UiState.Data<WeatherUi>).value
                    CurrentWeatherCard(weatherModel.currentWeather, weatherModel.settingsUnits)
                    Spacer(modifier = Modifier.height(16.dp))
                    ForecastWeatherCard(weatherModel.forecastWeather, ForecastMode.DAILY, weatherModel.settingsUnits)
                }
                is UiState.Error -> {

                }
                is UiState.Loading -> {
                    Box(Modifier.fillMaxSize()) {
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                }
            }
        }
    }
}



@Composable
fun WeatherForecastCard() {

}

@Composable
fun WeatherDetailedCard() {

}