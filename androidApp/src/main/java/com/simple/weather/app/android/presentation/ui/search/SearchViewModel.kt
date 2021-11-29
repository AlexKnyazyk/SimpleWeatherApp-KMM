package com.simple.weather.app.android.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.domain.usecase.SearchLocationUseCase
import com.simple.weather.app.android.presentation.model.SearchLocationResult
import com.simple.weather.app.android.presentation.model.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchLocationUseCase: SearchLocationUseCase
) : ViewModel() {

    private val searchQueryFlow = MutableSharedFlow<String>(extraBufferCapacity = 1)

    val uiState = searchQueryFlow
        .debounce(SEARCH_DEBOUNCE)
        .flatMapLatest { query ->
            searchLocationUiStateFlow(query)
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, UiState.data(SearchLocationResult.EMPTY))

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            searchQueryFlow.emit(query)
        }
    }

    private fun searchLocationUiStateFlow(query: String) = flow {
        emit(UiState.loading())
        val result = searchLocationUseCase(query)
        emit(result.fold(
            onSuccess = { UiState.data(SearchLocationResult(it, query.isNotBlank())) },
            onFailure = { UiState.error(it) }
        ))
    }

    class Factory(
        private val searchLocationUseCase: SearchLocationUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SearchViewModel(searchLocationUseCase) as T
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 500L
    }
}