package com.simple.weather.app.android.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.R
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.usecase.ISearchLocationUseCase
import com.simple.weather.app.android.presentation.ui.search.model.SearchLocationResult
import com.simple.weather.app.android.presentation.model.UiState
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
        val result = searchLocationUseCase(query)
        emit(result.fold(
            onSuccess = { searchModels ->
                UiState.data(SearchLocationResult(searchModels, query.isNotBlank()))
            },
            onFailure = { UiState.error(it) }
        ))
    }

    fun onItemClick(itemModel: SearchLocationModel) {
        //TODO
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 500L
    }
}