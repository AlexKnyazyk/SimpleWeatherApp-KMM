package com.simple.weather.app.android.presentation.ui.search

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.simple.weather.app.android.presentation.ui.search.model.SearchLocationResult
import com.simple.weather.app.domain.domain.model.SearchLocationModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun SearchLocationScreen(navController: NavHostController) {
    val viewModel = getViewModel<SearchViewModel>()

    val searchLocationResult by viewModel.searchLocationResult.collectAsState()

    CollectSearchScreenEvents(viewModel, navController)

    Column {
        SearchTextField(onTextChanged = { viewModel.setSearchQuery(it) })

        when (searchLocationResult) {
            is SearchLocationResult.Data -> {
                val dataResult = (searchLocationResult as SearchLocationResult.Data)
                SearchResultsContent(dataResult, onItemClick = { item ->
                    viewModel.onItemClick(item)
                })
            }
            is SearchLocationResult.Error -> {
                SearchErrorMessageContent(
                    error = (searchLocationResult as SearchLocationResult.Error).error
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
fun SearchTextField(onTextChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        var searchQuery by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { text ->
                searchQuery = text
                onTextChanged(text)
            },
            singleLine = true,
            label = { Text(text = stringResource(R.string.search_hint)) },
            trailingIcon = (if (searchQuery.isNotEmpty()) {
                {
                    IconButton(onClick = {
                        searchQuery = ""
                        onTextChanged("")
                    }) {
                        Image(imageVector = Icons.Default.Clear, contentDescription = null)
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
    dataResult: SearchLocationResult.Data,
    onItemClick: (SearchLocationModel) -> Unit
) {
    if (dataResult.hasSearchQuery && dataResult.itemModels.isEmpty()) {
        EmptySearchResultsContent()
    } else {
        val items = dataResult.itemModels
        LazyColumn() {
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
private fun EmptySearchResultsContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = stringResource(R.string.no_search_results),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun SearchErrorMessageContent(error: Throwable) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = error.message ?: stringResource(R.string.error_unknown),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}