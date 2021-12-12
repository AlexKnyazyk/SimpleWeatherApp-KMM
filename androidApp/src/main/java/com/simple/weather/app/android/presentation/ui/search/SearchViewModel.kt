package com.simple.weather.app.android.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.presentation.ui.search.model.SearchLocationResult
import com.simple.weather.app.domain.domain.model.SearchLocationModel
import com.simple.weather.app.domain.domain.usecase.search.IAddSearchLocationToFavoritesUseCase
import com.simple.weather.app.domain.domain.usecase.search.ISearchLocationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class SearchViewModel(
    private val searchLocationUseCase: ISearchLocationUseCase,
    private val addSearchLocationToFavoritesUseCase: IAddSearchLocationToFavoritesUseCase
) : ViewModel() {

    var searchQuery: String by Delegates.observable("") { _, _, query ->
        viewModelScope.launch {
            searchQueryFlow.emit(query)
        }
    }

    private val searchQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val searchLocationResult: StateFlow<SearchLocationResult> = searchQueryFlow
        .distinctUntilChanged()
        .debounce(SEARCH_DEBOUNCE)
        .flatMapLatest { query ->
            searchLocationUiStateFlow(query)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, SearchLocationResult.EMPTY)

    private val _events = MutableSharedFlow<SearchScreenEvents>()
    val events = _events.asSharedFlow()

    private fun searchLocationUiStateFlow(query: String): Flow<SearchLocationResult> = flow {
        emit(
            searchLocationUseCase(query).fold(
                onSuccess = { searchModels ->
                    SearchLocationResult.Data(searchModels, query.isNotBlank())
                },
                onFailure = { SearchLocationResult.Error(it) }
            )
        )
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