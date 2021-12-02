package com.simple.weather.app.android.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.usecase.search.IAddSearchLocationToFavoritesUseCase
import com.simple.weather.app.android.domain.usecase.search.ISearchLocationUseCase
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.search.model.SearchLocationResult
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchLocationUseCase: ISearchLocationUseCase,
    private val addSearchLocationToFavoritesUseCase: IAddSearchLocationToFavoritesUseCase
) : ViewModel() {

    private val searchQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val uiState: StateFlow<UiState<SearchLocationResult>> = searchQueryFlow
        .distinctUntilChanged()
        .debounce(SEARCH_DEBOUNCE)
        .flatMapLatest { query ->
            searchLocationUiStateFlow(query)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, UiState.data(SearchLocationResult.EMPTY))

    private val _events = MutableSharedFlow<SearchScreenEvents>()
    val events = _events.asSharedFlow()

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
        val result = searchLocationUseCase(query)
        emit(result.fold(
            onSuccess = { searchModels ->
                UiState.data(SearchLocationResult(searchModels, query.isNotBlank()))
            },
            onFailure = { UiState.error(it) }
        ))
    }

    fun onItemClick(itemModel: SearchLocationModel) {
        viewModelScope.launch {
            val event = addSearchLocationToFavoritesUseCase(itemModel).fold(
                onSuccess = { SearchScreenEvents.NavigateBack },
                onFailure = { SearchScreenEvents.ExistedFavoriteMessage }
            )
            _events.emit(event)
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 500L
    }
}