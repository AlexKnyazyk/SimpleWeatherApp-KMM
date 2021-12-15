package com.simple.weather.app.android.presentation.ui.base.weather.forecast

import android.text.format.DateUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.ui.base.weather.model.SettingsUnitsUi
import com.simple.weather.app.domain.model.ForecastModel
import java.util.Date

@Composable
fun ForecastItem(item: ForecastModel, settings: SettingsUnitsUi) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        ForecastDateTime(item)
        ForecastTemperature(item, settings)
        if (item is ForecastModel.Day) {
            ForecastTemperatureMinMax(item, settings)
        }
        ForecastConditionIcon(item)
        ForecastWind(item, settings)
    }
}

@Composable
private fun ForecastDateTime(item: ForecastModel) {
    val date = Date(item.dateMillis)
    val context = LocalContext.current
    Text(
        text = when (item) {
            is ForecastModel.Hour -> {
                DateUtils.formatDateTime(context, date.time, DateUtils.FORMAT_SHOW_TIME)
            }
            is ForecastModel.Day -> if (DateUtils.isToday(date.time)) {
                context.getString(R.string.today)
            } else {
                DateUtils.formatDateTime(
                    context,
                    date.time,
                    DateUtils.FORMAT_ABBREV_MONTH or DateUtils.FORMAT_SHOW_DATE
                )
            }
        },
        style = MaterialTheme.typography.caption
    )
}

@Composable
private fun ForecastTemperature(item: ForecastModel, settings: SettingsUnitsUi) {
    Text(
        text = if (settings.isTempMetric) {
            stringResource(R.string.temperature_c_format, item.temperatureC)
        } else {
            stringResource(R.string.temperature_f_format, item.temperatureF)
        },
        style = MaterialTheme.typography.h4
    )
}

@Composable
private fun ForecastTemperatureMinMax(item: ForecastModel.Day, settings: SettingsUnitsUi) {
    val minTemp = if (settings.isTempMetric) {
        stringResource(R.string.temperature_c_format, item.temperatureMinC)
    } else {
        stringResource(R.string.temperature_f_format, item.temperatureMinF)
    }
    val maxTemp = if (settings.isTempMetric) {
        stringResource(R.string.temperature_c_format, item.temperatureMaxC)
    } else {
        stringResource(R.string.temperature_f_format, item.temperatureMaxF)
    }
    Text(
        text = "$minTemp / $maxTemp",
        style = MaterialTheme.typography.body2
    )
}

@Composable
private fun ForecastConditionIcon(item: ForecastModel) {
    Image(
        painter = rememberImagePainter(item.iconUrl),
        contentDescription = null,
        modifier = Modifier.size(40.dp)
    )
}

@Composable
private fun ForecastWind(item: ForecastModel, settings: SettingsUnitsUi) {
    val windSpeed = if (settings.isDistanceMetric) {
        stringResource(R.string.kmh_format, item.windSpeedKph)
    } else {
        stringResource(R.string.mph_format, item.windSpeedMph)
    }
    Text(
        text = windSpeed + if (item is ForecastModel.Hour) "\n(${item.windDir})" else "",
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center
    )
}


@Preview
@Composable
private fun ForecastItem_Preview() {
    val item = ForecastModel.Day(
        dateMillis = Date().time,
        temperatureC = 8,
        temperatureF = 8,
        temperatureMaxC = 10,
        temperatureMaxF = 2,
        temperatureMinC = 2,
        temperatureMinF = -2,
        iconUrl = "",
        windSpeedKph = 20.0,
        windSpeedMph = 30.0
    )
    ForecastItem(item, SettingsUnitsUi())
}