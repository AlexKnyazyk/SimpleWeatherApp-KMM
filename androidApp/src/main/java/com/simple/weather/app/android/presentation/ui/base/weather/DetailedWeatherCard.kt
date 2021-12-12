package com.simple.weather.app.android.presentation.ui.base.weather

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.ui.base.RoundedCard
import com.simple.weather.app.android.presentation.ui.base.weather.model.DetailedWeatherUi
import com.simple.weather.app.android.presentation.ui.base.weather.model.SettingsUnitsUi

@Composable
fun DetailedWeatherCard(
    model: DetailedWeatherUi,
    settings: SettingsUnitsUi,
    modifier: Modifier = Modifier
) {
    RoundedCard(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            WindDetailedWeatherRow(model, settings)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            HumidityDetailedWeatherRow(model)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            PressureDetailedWeatherRow(model)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            VisibilityDetailedWeatherRow(model, settings)

            Divider(modifier = Modifier.padding(vertical = 8.dp))

            IndexUVDetailedWeatherRow(model)
        }
    }
}

@Composable
private fun WindDetailedWeatherRow(
    model: DetailedWeatherUi,
    settings: SettingsUnitsUi,
) {
    DetailedWeatherRow(
        iconRes = R.drawable.ic_wind,
        titleRes = R.string.wind,
        value = if (settings.isDistanceMetric) {
            stringResource(R.string.kmh_format, model.windKph)
        } else {
            stringResource(R.string.mph_format, model.windMph)
        }
    )
}

@Composable
private fun HumidityDetailedWeatherRow(
    model: DetailedWeatherUi
) {
    DetailedWeatherRow(
        iconRes = R.drawable.ic_humidity,
        titleRes = R.string.humidity,
        value = stringResource(R.string.percent_format, model.humidity)
    )
}

@Composable
private fun PressureDetailedWeatherRow(
    model: DetailedWeatherUi
) {
    DetailedWeatherRow(
        iconRes = R.drawable.ic_barometer,
        titleRes = R.string.pressure,
        value = stringResource(R.string.pressure_mbar_format, model.pressureMb)
    )
}

@Composable
private fun DetailedWeatherRow(
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    value: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            tint = MaterialTheme.colors.primary,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(stringResource(id = titleRes), style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = value, style = MaterialTheme.typography.body1)
    }
}

@Composable
private fun VisibilityDetailedWeatherRow(
    model: DetailedWeatherUi,
    settings: SettingsUnitsUi,
) {
    DetailedWeatherRow(
        iconRes = R.drawable.ic_witness,
        titleRes = R.string.visibility,
        value = if (settings.isDistanceMetric) {
            stringResource(R.string.kmh_format, model.visibilityKm)
        } else {
            stringResource(R.string.mph_format, model.visibilityMiles)
        }
    )
}

@Composable
private fun IndexUVDetailedWeatherRow(
    model: DetailedWeatherUi
) {
    DetailedWeatherRow(
        iconRes = R.drawable.ic_sun,
        titleRes = R.string.index_uv,
        value = model.indexUv.toString()
    )
}

@Preview
@Composable
private fun DetailedWeatherCard_Preview() {
    val model = DetailedWeatherUi(
        windKph = 0.0,
        windMph = 0.0,
        windDir = "S",
        humidity = 1,
        pressureMb = 0.0,
        visibilityKm = 0.0,
        visibilityMiles = 0.0,
        indexUv = 0
    )
    val settings = SettingsUnitsUi()
    DetailedWeatherCard(model, settings)
}