package com.simple.weather.app.android.presentation.ui.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.simple.weather.app.android.presentation.navigation.Routes

@Composable
fun FavoriteLocationsListScreen(rootNavController: NavHostController, navController: NavHostController) {
    Column {
        Text(text = "FavoriteLocationsList")
        Button(onClick = { rootNavController.navigate(Routes.SEARCH_LOCATION) }) {
            Text(text = "to search")
        }
        Button(onClick = {
            navController.navigate(Routes.LocationWeather.route(2))
        }) {
            Text(text = "to details")
        }
    }
}