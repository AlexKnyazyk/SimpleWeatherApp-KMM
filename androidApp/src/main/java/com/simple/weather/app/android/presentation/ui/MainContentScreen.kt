package com.simple.weather.app.android.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simple.weather.app.android.presentation.navigation.Routes
import com.simple.weather.app.android.presentation.ui.main.MainTabsScreen
import com.simple.weather.app.android.presentation.ui.search.SearchLocationScreen
import com.simple.weather.app.android.presentation.ui.settings.SettingsScreen

@Composable
fun MainContentScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.MAIN_TABS) {
        composable(Routes.MAIN_TABS) { MainTabsScreen(navController) }
        composable(Routes.SEARCH_LOCATION) { SearchLocationScreen(navController) }
        composable(Routes.SETTINGS) { SettingsScreen(navController) }
    }
}

@Preview
@Composable
fun MainContentPreview() {
    MainContentScreen()
}