package com.simple.weather.app.android.presentation.ui.favorites

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.navigation.Routes
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteLocationsListScreen(
    rootNavController: NavHostController,
    navController: NavHostController
) {
    val viewModel = getViewModel<FavoritesViewModel>()

    val favoriteLocations by viewModel.favoriteLocations.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        AddFavoriteLocationFabContent {
            rootNavController.navigate(Routes.SEARCH_LOCATION)
        }

        if (favoriteLocations.isEmpty()) {
            EmptyFavoritesHintContent()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(all = 16.dp),
            ) {
                itemsIndexed(favoriteLocations) { index, item ->
                    FavoriteLocationItemContent(
                        item = item,
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.LocationWeather.route(item.id))
                        }
                    )
                    if (index != favoriteLocations.lastIndex) {
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun AddFavoriteLocationFabContent(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(onClick = onClick) {
            Image(
                painter = painterResource(R.drawable.ic_baseline_add_24),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun EmptyFavoritesHintContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.favorites_empty_hint),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}