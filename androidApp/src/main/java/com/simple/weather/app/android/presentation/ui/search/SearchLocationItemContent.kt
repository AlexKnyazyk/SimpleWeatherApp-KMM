package com.simple.weather.app.android.presentation.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simple.weather.app.domain.model.SearchLocationModel

@Composable
fun SearchLocationItemContent(
    modifier: Modifier = Modifier,
    item: SearchLocationModel,
    onClick: () -> Unit,
) {
    TextButton(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colors.onBackground,
        ),
        contentPadding = PaddingValues(all = 16.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
            Text(text = item.name, style = MaterialTheme.typography.h6)
            Text(text = item.region, style = MaterialTheme.typography.subtitle1)
            Text(text = item.country, style = MaterialTheme.typography.subtitle2)
        }
    }
}

@Preview
@Composable
private fun SearchLocationItemContent_Preview() {
    val item = SearchLocationModel(
        0,
        "Minsk",
        "Minsk region",
        "Belarus"
    )
    SearchLocationItemContent(item = item) {}
}