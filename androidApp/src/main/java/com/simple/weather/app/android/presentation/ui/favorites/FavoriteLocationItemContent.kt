package com.simple.weather.app.android.presentation.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.ui.base.RoundedCard
import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationItemUi

@Composable
fun FavoriteLocationItemContent(
    item: FavoriteLocationItemUi,
    modifier: Modifier = Modifier
) {
    RoundedCard(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.padding(all = 16.dp)) {
                Text(text = item.name, style = MaterialTheme.typography.body1)
                Text(text = item.region, style = MaterialTheme.typography.body2)
                Text(text = item.country, style = MaterialTheme.typography.caption)
            }
            Spacer(Modifier.weight(1f))
            if (item.tempC != null && item.tempF != null) {
                Text(
                    text = if (item.isTempMetric) {
                        stringResource(R.string.temperature_c_format, item.tempC)
                    } else {
                        stringResource(R.string.temperature_f_format, item.tempF)
                    },
                    style = MaterialTheme.typography.h4
                )
            }
            if (item.weatherConditionIconUrl != null) {
                Image(
                    painter = rememberImagePainter(item.weatherConditionIconUrl),
                    contentDescription = null,
                    modifier = Modifier.size(60.dp)
                )
                Spacer(Modifier.width(16.dp))
            }
        }
    }
}

@Preview
@Composable
fun FavoriteLocationItemContent_Preview() {
    val item = FavoriteLocationItemUi(
        0,
        "Minsk",
        "Minsk region",
        "Belarus",
        0,
        0,
        "",
        true
    )
    FavoriteLocationItemContent(item)
}