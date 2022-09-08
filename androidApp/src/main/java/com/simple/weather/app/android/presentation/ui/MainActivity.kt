package com.simple.weather.app.android.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.simple.weather.app.android.presentation.theme.DarkColorPalette
import com.simple.weather.app.android.presentation.theme.LightColorPalette

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            MaterialTheme(
                colors = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette
            ) {
                Box(Modifier.systemBarsPadding()) {
                    MainContentScreen()
                }

                val color = MaterialTheme.colors.primarySurface
                SideEffect {
                    systemUiController.setStatusBarColor(color = color)
                }
            }
        }
    }
}