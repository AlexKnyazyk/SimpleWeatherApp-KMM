package com.simple.weather.app.android.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.simple.weather.app.android.presentation.theme.DarkColorPalette
import com.simple.weather.app.android.presentation.theme.LightColorPalette

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
            ) {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
                    MainContentScreen()
                }
            }
        }
    }
}