package com.simple.weather.app.android.presentation.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.simple.weather.app.android.presentation.ui.base.ToolbarBackIcon
import com.simple.weather.app.android.presentation.ui.details.LocationWeatherScreen
import com.simple.weather.app.android.presentation.ui.favorites.FavoriteLocationsListScreen
import com.simple.weather.app.android.presentation.ui.home.HomeWeatherScreen

@Composable
fun MainTabsScreen(rootNavController: NavHostController) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_title)) },
                actions = { SettingsIconButton(rootNavController) },
                navigationIcon = navigationArrowIconContent(currentDestination, navController)
            )
        },
        bottomBar = {
            BottomNavigation {
                mainTabs.forEach { tab ->
                    MainBottomNavigationItem(tab, currentDestination, navController)
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Routes.HOME, Modifier.padding(innerPadding)) {
            composable(Routes.HOME) { HomeWeatherScreen() }
            composable(Routes.FAVORITES) {
                FavoriteLocationsListScreen(rootNavController, navController)
            }
            composable(
                Routes.LocationWeather.NAME,
                arguments = listOf(
                    navArgument(Routes.LocationWeather.LOCATION_ID) { type = NavType.IntType }
                )
            ) { backStackEntry ->
                LocationWeatherScreen(
                    locationId = backStackEntry.arguments?.getInt(Routes.LocationWeather.LOCATION_ID)
                        ?: throw IllegalArgumentException("Missed location id arg")
                )
            }
        }
    }
}

private fun navigationArrowIconContent(
    currentDestination: NavDestination?,
    navController: NavHostController
): (@Composable () -> Unit)? {
    return if (currentDestination?.route !in mainTabs.map { it.route }) {
        {
            ToolbarBackIcon(onBackClick = { navController.popBackStack() })
        }
    } else null
}

@Composable
private fun SettingsIconButton(navController: NavHostController) {
    IconButton(onClick = {
        navController.navigate(Routes.SETTINGS)
    }) {
        Icon(
            imageVector = Icons.Default.Settings,
            tint = Color.White,
            contentDescription = null
        )
    }
}

@Composable
private fun RowScope.MainBottomNavigationItem(
    tab: MainTab,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == tab.route } == true
    val selectionColor = if (isSelected) {
        MaterialTheme.colors.secondary
    } else {
        Color.White.copy(alpha = 0.4f)
    }
    BottomNavigationItem(
        icon = {
            Icon(
                painter = painterResource(tab.iconRes),
                tint = selectionColor,
                contentDescription = null
            )
        },
        label = { Text(stringResource(tab.titleRes), color = selectionColor) },
        selected = isSelected,
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

private sealed class MainTab(
    val route: String,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
) {
    object Home : MainTab(Routes.HOME, R.string.title_home, R.drawable.ic_home)
    object Favorites :
        MainTab(Routes.FAVORITES, R.string.title_favorites, R.drawable.ic_favorites)
}