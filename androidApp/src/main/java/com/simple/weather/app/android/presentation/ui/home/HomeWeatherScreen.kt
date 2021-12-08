package com.simple.weather.app.android.presentation.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.base.weather.model.WeatherModelUi
import com.simple.weather.app.domain.domain.model.WeatherModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeWeatherScreen() {
    val viewModel = getViewModel<HomeViewModel>()
    val uiState by viewModel.uiState.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(uiState is UiState.Loading && (uiState as UiState.Loading<WeatherModelUi>).pullToRefresh),
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
                    CurrentWeatherCard((uiState as UiState.Data<WeatherModelUi>).value)
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
fun CurrentWeatherCard(modelUi: WeatherModelUi) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(0.5.dp, Color.Gray),
        elevation = 4.dp,
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Icon(painterResource(R.drawable.ic_location), contentDescription = null)
                Text(
                    text = stringResource(R.string.location_name_format, modelUi.model.locationName, modelUi.model.locationCountry),
                    style = MaterialTheme.typography.body1
                )
            }
            Text(
                text = stringResource(R.string.last_update_format, modelUi.model.lastUpdated),
                style = MaterialTheme.typography.caption
            )
            Row {
                Column {
                    Text(
                        text = if (modelUi.isTempMetric) {
                            stringResource(R.string.temperature_c_format, modelUi.model.tempC)
                        } else {
                            stringResource(R.string.temperature_f_format, modelUi.model.tempF)
                        },
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        text = stringResource(
                            R.string.feels_like_format, if (modelUi.isTempMetric) {
                                stringResource(R.string.temperature_c_format, modelUi.model.tempFeelsLikeC)
                            } else {
                                stringResource(R.string.temperature_f_format, modelUi.model.tempFeelsLikeF)
                            }
                        ),
                        style = MaterialTheme.typography.body2
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = rememberImagePainter(modelUi.model.weatherConditionIconUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                )
            }
            Text(
                text = modelUi.model.weatherCondition,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
fun CurrentWeatherCard_Preview() {
    val model = WeatherModel(
        locationName = "locationName",
        locationCountry = "locationCountry",
        lastUpdated = "lastUpdated",
        tempC = 0,
        tempF = 0,
        tempFeelsLikeC = 0,
        tempFeelsLikeF = 0,
        weatherCondition = "weatherCondition",
        weatherConditionIconUrl = "weatherConditionIconUrl",
        windKph = 0.0,
        windMph = 0.0,
        windDir = "dir",
        humidity = 0,
        pressureMb = 0.0,
        visibilityKm = 0.0,
        visibilityMiles = 0.0,
        indexUv = 0,
        forecastDaily = emptyList(),
        forecastHourly = emptyList(),
    )
    val modelUi = WeatherModelUi(model, true, true)
    CurrentWeatherCard(modelUi)
}

@Composable
fun WeatherForecastCard() {

}

@Composable
fun WeatherDetailedCard() {

}