package com.simple.weather.app.android.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.R
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.usecase.ISearchLocationUseCase
import com.simple.weather.app.android.presentation.ui.search.model.SearchLocationResult
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.search.model.SearchItemUi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchLocationUseCase: ISearchLocationUseCase
) : ViewModel() {

    private val searchQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val uiState: StateFlow<UiState<SearchLocationResult>> = searchQueryFlow
        .distinctUntilChanged()
        .debounce(SEARCH_DEBOUNCE)
        .flatMapLatest { query ->
            searchLocationUiStateFlow(query)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, UiState.data(SearchLocationResult.EMPTY))

    init {
        setSearchQuery("")
    }

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            searchQueryFlow.emit(query)
        }
    }

    private fun searchLocationUiStateFlow(query: String): Flow<UiState<SearchLocationResult>> = flow {
        emit(UiState.loading())
        val favorites = listOf<SearchLocationModel>(
            SearchLocationModel(
                2801268,
                51.52,
                -0.11,
                "London, City of London, Greater London, United Kingdom",
                "City of London, Greater London",
                "United Kingdom"
            )
        ) //TODO
        val result = searchLocationUseCase(query)
        emit(result.fold(
            onSuccess = { searchModels ->
                UiState.data(
                    SearchLocationResult(
                        buildFavoriteItems(favorites) + buildSearchItems(searchModels),
                        query.isNotBlank()
                    )
                )
            },
            onFailure = { UiState.error(it) }
        ))
    }

    private fun buildFavoriteItems(favorites: List<SearchLocationModel>): List<SearchItemUi> {
        return if (favorites.isNotEmpty()) {
            listOf<SearchItemUi>(SearchItemUi.HeaderItem(R.string.favorites_header))
        } else {
            emptyList()
        } + favorites.map { SearchItemUi.FavoriteItem(it) }
    }

    private fun buildSearchItems(searchModels: List<SearchLocationModel>): List<SearchItemUi> {
        return if (searchModels.isNotEmpty()) {
            listOf<SearchItemUi>(SearchItemUi.HeaderItem(R.string.search_results_header))
        } else {
            emptyList()
        } + searchModels.map { SearchItemUi.SearchItem(it) }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 500L
    }
}