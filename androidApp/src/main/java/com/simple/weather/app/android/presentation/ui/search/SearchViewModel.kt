package com.simple.weather.app.android.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.domain.usecase.SearchLocationUseCase
import com.simple.weather.app.android.presentation.model.SearchLocationResult
import com.simple.weather.app.android.presentation.model.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchLocationUseCase: SearchLocationUseCase
) : ViewModel() {

    private val searchQueryFlow = MutableSharedFlow<String>(
        extraBufferCapacity = 1
    )

    private val _uiState = MutableLiveData<UiState<SearchLocationResult>>()
    val uiState: LiveData<UiState<SearchLocationResult>> = _uiState

    init {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(SEARCH_DEBOUNCE)
                .flatMapLatest { query ->
                    searchLocationUiStateFlow(query)
                }
                .collect { state ->
                    _uiState.value = state
                }
        }
    }

    fun setSearchQuery(query: String) {
        searchQueryFlow.tryEmit(query)
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
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchViewModel(searchLocationUseCase) as T
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 500L
    }
}