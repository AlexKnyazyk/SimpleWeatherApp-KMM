package com.simple.weather.app.android.presentation.ui.base.weather.forecast

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
    settings: SettingsUnitsUi,
    mode: ForecastMode,
    onModeSelected: (ForecastMode) -> Unit,
    modifier: Modifier = Modifier
) {
    RoundedCard(modifier) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedButtonsGroup(
                textList = ForecastMode.values().map { stringResource(it.textRes) },
                mode.ordinal,
                onSelected = { index -> onModeSelected(ForecastMode.values()[index]) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
            )
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                LazyRow {
                    val items = when (mode) {
                        ForecastMode.HOURLY -> model.forecastHourly
                        ForecastMode.DAILY -> model.forecastDaily
                    }
                    items(count = items.size) { index ->
                        ForecastItem(items[index], settings)
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
    val mode = remember { mutableStateOf(ForecastMode.HOURLY) }
    ForecastWeatherCard(model, settings, mode.value, { mode.value = it })
}