package com.simple.weather.app.android.presentation.ui.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.navigation.Routes
import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationItemUi
import com.simple.weather.app.android.presentation.ui.utils.debouncedClickable
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoriteLocationsListScreen(
    rootNavController: NavHostController,
    navController: NavHostController
) {
    val viewModel = getViewModel<FavoritesViewModel>()

    val favoriteLocations by viewModel.favoriteLocations.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        if (favoriteLocations.isEmpty()) {
            EmptyFavoritesHintContent()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(all = 16.dp),
            ) {
                itemsIndexed(
                    favoriteLocations,
                    key = { _, item -> item.id }
                ) { index, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateItemPlacement()
                            .padding(bottom = (if (index != favoriteLocations.lastIndex) 16.dp else 0.dp))
                    ) {
                        FavoriteLocationRemovableItemContent(
                            item,
                            onDeleteItem = { viewModel.deleteFavorite(it) },
                            onItemClick = {
                                navController.navigate(Routes.LocationWeather.route(it.id))
                            }
                        )
                    }
                }
            }
        }

        AddFavoriteLocationFabContent {
            rootNavController.navigate(Routes.SEARCH_LOCATION)
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
            Icon(
                imageVector = Icons.Default.Add,
                tint = MaterialTheme.colors.onSecondary,
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

@Composable
private fun FavoriteLocationRemovableItemContent(
    item: FavoriteLocationItemUi,
    onDeleteItem: (FavoriteLocationItemUi) -> Unit,
    onItemClick: (FavoriteLocationItemUi) -> Unit
) {
    val dismissedValues = setOf(DismissValue.DismissedToEnd, DismissValue.DismissedToStart)
    val dismissState = rememberDismissState(
        confirmStateChange = { value ->
            if (value in dismissedValues) {
                onDeleteItem(item)
            }
            value !in dismissedValues
        }
    )

    SwipeToDismiss(
        state = dismissState,
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
            FavoriteDismissBackgroundContent(direction)
        },
    ) {
        FavoriteLocationItemContent(
            item = item,
            modifier = Modifier.debouncedClickable { onItemClick(item) }
        )
    }
}

@Composable
private fun FavoriteDismissBackgroundContent(direction: DismissDirection) {
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    Card(
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        backgroundColor = Color.Red,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp),
            contentAlignment = alignment
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}