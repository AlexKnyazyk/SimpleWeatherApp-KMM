package com.simple.weather.app.android.presentation.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.navigation.Routes
import com.simple.weather.app.android.presentation.ui.details.LocationWeatherScreen
import com.simple.weather.app.android.presentation.ui.favorites.FavoriteLocationsListScreen
import com.simple.weather.app.android.presentation.ui.home.HomeWeatherScreen

@Composable
fun MainTabsScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_title)) },
                actions = { SettingsIconButton(rootNavController) }
            )
        },
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                mainTabs.forEach { tab ->
                    MainBottomNavigationItem(tab, currentDestination, navController)
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Routes.HOME, Modifier.padding(innerPadding)) {
            composable(Routes.HOME) { HomeWeatherScreen() }
            composable(Routes.FAVORITES) { FavoriteLocationsListScreen(rootNavController, navController) }
            composable(
                Routes.LocationWeather.NAME,
                arguments = listOf(navArgument(Routes.LocationWeather.LOCATION_ID) { type = NavType.IntType })
            ) { backStackEntry ->
                LocationWeatherScreen(
                    locationId = backStackEntry.arguments?.getInt(Routes.LocationWeather.LOCATION_ID)
                        ?: throw IllegalAccessException("Missed location id arg")
                )
            }
        }
    }
}

@Composable
private fun SettingsIconButton(navController: NavHostController) {
    IconButton(onClick = {
        navController.navigate(Routes.SETTINGS)
    }) {
        Icon(painter = painterResource(R.drawable.ic_baseline_settings_24), contentDescription = null)
    }
}

@Composable
private fun RowScope.MainBottomNavigationItem(tab: MainTab, currentDestination: NavDestination?, navController: NavHostController) {
    BottomNavigationItem(
        icon = { Image(painter = painterResource(tab.iconRes), contentDescription = null) },
        label = { Text(stringResource(tab.titleRes)) },
        selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
        onClick = {
            navController.navigate(tab.route) {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    )
}

private val mainTabs = listOf(MainTab.Home, MainTab.Favorites)

private sealed class MainTab(val route: String, @StringRes val titleRes: Int, @DrawableRes val iconRes: Int) {
    object Home : MainTab(Routes.HOME, R.string.title_home, R.drawable.ic_home_black_24dp)
    object Favorites : MainTab(Routes.FAVORITES, R.string.title_favorites, R.drawable.ic_baseline_star_24)
}