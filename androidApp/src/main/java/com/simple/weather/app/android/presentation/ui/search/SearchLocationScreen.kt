package com.simple.weather.app.android.presentation.ui.search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.simple.weather.app.android.R
import com.simple.weather.app.android.presentation.ui.error.toUiErrorMessage
import com.simple.weather.app.android.presentation.ui.search.model.SearchLocationUiState
import com.simple.weather.app.domain.domain.model.SearchLocationModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchLocationScreen(navController: NavHostController) {
    val viewModel = getViewModel<SearchViewModel>()

    val searchLocationState by viewModel.searchLocationState.collectAsState()

    CollectSearchScreenEvents(viewModel, navController)

    Column {
        SearchTextField(
            query = viewModel.searchQuery,
            onTextChanged = { viewModel.searchQuery = it }
        )

        when (searchLocationState) {
            SearchLocationUiState.Idle -> {
                // no views
            }
            is SearchLocationUiState.Data -> {
                val searchLocationData = (searchLocationState as SearchLocationUiState.Data)
                SearchResultsContent(searchLocationData, onItemClick = { item ->
                    viewModel.onItemClick(item)
                })
            }
            is SearchLocationUiState.Error -> {
                SearchMessageContent(
                    message = (searchLocationState as SearchLocationUiState.Error).error.toUiErrorMessage()
                )
            }
        }
    }
}

@Composable
private fun CollectSearchScreenEvents(
    viewModel: SearchViewModel,
    navController: NavHostController
) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        lifecycleOwner.lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    SearchScreenEvents.NavigateBack -> {
                        navController.popBackStack()
                    }
                    SearchScreenEvents.ExistedFavoriteMessage -> {
                        Toast.makeText(
                            context,
                            R.string.location_already_added_to_favorites,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchTextField(query: String, onTextChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var searchQuery by remember { mutableStateOf(query) }
        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { text ->
                searchQuery = text
                onTextChanged(text)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = MaterialTheme.colors.onSurface
            ),
            singleLine = true,
            label = { Text(text = stringResource(R.string.search_hint)) },
            trailingIcon = (if (searchQuery.isNotEmpty()) {
                {
                    IconButton(onClick = {
                        searchQuery = ""
                        onTextChanged("")
                    }) {
                        Image(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                        )
                    }
                }
            } else null),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { focusManager.clearFocus() }
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SearchResultsContent(
    searchLocationData: SearchLocationUiState.Data,
    onItemClick: (SearchLocationModel) -> Unit
) {
    if (searchLocationData.itemModels.isEmpty()) {
        SearchMessageContent(message = stringResource(R.string.no_search_results))
    } else {
        val items = searchLocationData.itemModels
        LazyColumn {
            itemsIndexed(items) { index, item ->
                SearchLocationItemContent(
                    item = item,
                    onClick = {
                        onItemClick(item)
                    }
                )
                if (index != items.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun SearchMessageContent(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 50.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onBackground
        )
    }
}