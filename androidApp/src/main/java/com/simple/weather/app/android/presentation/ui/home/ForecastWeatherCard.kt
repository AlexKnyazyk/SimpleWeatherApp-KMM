package com.simple.weather.app.android.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simple.weather.app.android.presentation.model.ForecastMode
import com.simple.weather.app.android.presentation.ui.base.OutlinedButtonsGroup
import com.simple.weather.app.android.presentation.ui.base.RoundedCard
import com.simple.weather.app.android.presentation.ui.base.weather.model.ForecastWeatherUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.SettingsUnitsUi

@Composable
fun ForecastWeatherCard(
    model: ForecastWeatherUi,
    mode: ForecastMode,
    settings: SettingsUnitsUi,
    modifier: Modifier = Modifier
) {
    RoundedCard(modifier) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            val forecastModeState = remember { mutableStateOf(mode) }
            OutlinedButtonsGroup(
                textList = ForecastMode.values().map { stringResource(it.textRes) },
                forecastModeState.value.ordinal,
                onSelected = { index ->
                    forecastModeState.value = ForecastMode.values()[index]
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                LazyRow {
                    when (forecastModeState.value) {
                        ForecastMode.HOURLY -> {
                            items(count = model.forecastHourly.size) { index ->
                                ForecastItem(model.forecastHourly[index], settings)
                            }
                        }
                        ForecastMode.DAILY -> {
                            items(count = model.forecastDaily.size) { index ->
                                ForecastItem(model.forecastDaily[index], settings)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ForecastWeatherCard_Preview() {
    val model = ForecastWeatherUi(
        forecastHourly = emptyList(),
        forecastDaily = emptyList()
    )
    val settings = SettingsUnitsUi()
    ForecastWeatherCard(model, ForecastMode.HOURLY, settings)
}