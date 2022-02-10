package com.simple.weather.app.android.presentation.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.simple.weather.app.android.presentation.navigation.Routes
import com.simple.weather.app.android.presentation.ui.main.MainTabsScreen
import com.simple.weather.app.android.presentation.ui.search.SearchLocationScreen
import com.simple.weather.app.android.presentation.ui.settings.SettingsScreen
import com.simple.weather.app.android.presentation.ui.utils.slideInLeft
import com.simple.weather.app.android.presentation.ui.utils.slideInRight
import com.simple.weather.app.android.presentation.ui.utils.slideOutLeft
import com.simple.weather.app.android.presentation.ui.utils.slideOutRight

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainContentScreen() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Routes.MAIN_TABS) {
        composable(
            route = Routes.MAIN_TABS,
            exitTransition = {
                when (targetState.destination.route) {
                    Routes.SETTINGS -> slideOutLeft()
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Routes.SETTINGS -> slideInRight()
                    else -> null
                }
            }
        ) { MainTabsScreen(navController) }

        composable(
            route = Routes.SEARCH_LOCATION,
            enterTransition = { expandIn(animationSpec = tween(400), expandFrom = Alignment.Center) },
            popExitTransition = { shrinkOut(animationSpec = tween(400), shrinkTowards = Alignment.Center) }
        ) { SearchLocationScreen(navController) }

        composable(
            route = Routes.SETTINGS,
            enterTransition = { slideInLeft() },
            popExitTransition = { slideOutRight() }
        ) { SettingsScreen(navController) }
    }
}

@Preview
@Composable
fun MainContentPreview() {
    MainContentScreen()
}