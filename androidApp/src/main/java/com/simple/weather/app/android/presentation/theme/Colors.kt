package com.simple.weather.app.android.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val LightColorPalette = lightColors()

val DarkColorPalette = darkColors()

@Preview
@Composable
private fun MaterialColorsPreview_Light() {
    MaterialTheme(colors = LightColorPalette) {
        MaterialColorsPreview()
    }
}

@Preview
@Composable
private fun MaterialColorsPreview_Dark() {
    MaterialTheme(colors = DarkColorPalette) {
        MaterialColorsPreview()
    }
}

@Composable
private fun MaterialColorsPreview() {
    Column(Modifier.width(400.dp)) {
        ColorPreviewRow(
            colorName = "Primary",
            color = MaterialTheme.colors.primary,
            onColorName = "On Primary",
            onColor = MaterialTheme.colors.onPrimary
        )
        Divider()
        ColorPreviewRow(
            colorName = "PrimaryVariant",
            color = MaterialTheme.colors.primaryVariant,
            onColorName = "On Primary",
            onColor = MaterialTheme.colors.onPrimary
        )
        Divider()
        ColorPreviewRow(
            colorName = "Secondary",
            color = MaterialTheme.colors.secondary,
            onColorName = "On Secondary",
            onColor = MaterialTheme.colors.onSecondary
        )
        Divider()
        ColorPreviewRow(
            colorName = "SecondaryVariant",
            color = MaterialTheme.colors.secondaryVariant,
            onColorName = "On Secondary",
            onColor = MaterialTheme.colors.onSecondary
        )
        Divider()
        ColorPreviewRow(
            colorName = "Background",
            color = MaterialTheme.colors.background,
            onColorName = "On Background",
            onColor = MaterialTheme.colors.onBackground
        )
        Divider()
        ColorPreviewRow(
            colorName = "Surface",
            color = MaterialTheme.colors.surface,
            onColorName = "On Surface",
            onColor = MaterialTheme.colors.onSurface
        )
        Divider()
        ColorPreviewRow(
            colorName = "Error",
            color = MaterialTheme.colors.error,
            onColorName = "On Error",
            onColor = MaterialTheme.colors.onError
        )
    }
}

@Composable
private fun ColorPreviewRow(colorName: String, color: Color, onColorName: String, onColor: Color) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = color)
    ) {
        Text(colorName, color = onColor)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            onColorName,
            style = MaterialTheme.typography.h4,
            color = onColor
        )
    }
}