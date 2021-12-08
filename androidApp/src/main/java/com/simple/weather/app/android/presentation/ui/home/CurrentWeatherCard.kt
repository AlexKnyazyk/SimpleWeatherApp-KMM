package com.simple.weather.app.android.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.ui.base.RoundedCard
import com.simple.weather.app.android.presentation.ui.base.weather.model.CurrentWeatherUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.SettingsUnitsUi

@Composable
fun CurrentWeatherCard(
    model: CurrentWeatherUi,
    settings: SettingsUnitsUi,
    modifier: Modifier = Modifier
) {
    RoundedCard(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                Icon(painterResource(R.drawable.ic_location), contentDescription = null)
                Text(
                    text = stringResource(
                        R.string.location_name_format,
                        model.locationName,
                        model.locationCountry
                    ),
                    style = MaterialTheme.typography.body1
                )
            }
            Text(
                text = stringResource(R.string.last_update_format, model.lastUpdated),
                style = MaterialTheme.typography.caption
            )
            Row {
                Column {
                    Text(
                        text = if (settings.isTempMetric) {
                            stringResource(R.string.temperature_c_format, model.tempC)
                        } else {
                            stringResource(R.string.temperature_f_format, model.tempF)
                        },
                        style = MaterialTheme.typography.h2
                    )
                    Text(
                        text = stringResource(
                            R.string.feels_like_format,
                            if (settings.isTempMetric) {
                                stringResource(
                                    R.string.temperature_c_format,
                                    model.tempFeelsLikeC
                                )
                            } else {
                                stringResource(
                                    R.string.temperature_f_format,
                                    model.tempFeelsLikeF
                                )
                            }
                        ),
                        style = MaterialTheme.typography.body2
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = rememberImagePainter(model.weatherConditionIconUrl),
                    contentDescription = null,
                    modifier = Modifier.size(90.dp)
                )
            }
            Text(
                text = model.weatherCondition,
                style = MaterialTheme.typography.h5
            )
        }
    }
}

@Preview
@Composable
fun CurrentWeatherCard_Preview() {
    val model = CurrentWeatherUi(
        locationName = "Minsk",
        locationCountry = "Belarus",
        lastUpdated = "21.12.2012 16:00",
        tempC = 10,
        tempF = 4,
        tempFeelsLikeC = 10,
        tempFeelsLikeF = 4,
        weatherCondition = "Sunny",
        weatherConditionIconUrl = "weatherConditionIconUrl"
    )
    val settings = SettingsUnitsUi()
    CurrentWeatherCard(model, settings)
}