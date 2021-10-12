package com.simple.weather.app.android.presentation.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.presentation.model.SearchLocationResult
import com.simple.weather.app.android.presentation.model.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val searchQueryFlow = MutableSharedFlow<String>(
        extraBufferCapacity = 1
    )

    private val _uiState = MutableLiveData<UiState<SearchLocationResult>>()
    val uiState: LiveData<UiState<SearchLocationResult>> = _uiState

    init {
        viewModelScope.launch {
            searchQueryFlow
                .debounce(SEARCH_DEBOUNCE)
                .collect {
                    Log.i("SearchViewModel", "collect=$it")
                    _uiState.value = UiState.data(
                        SearchLocationResult(
                            itemModels = listOf(
                                SearchLocationModel(1, 0.0, 0.0, "London", "London Region", "UK"),
                                SearchLocationModel(2, 0.0, 0.0, "London2", "London Region2", "UK")
                            ),
                            hasSearchQuery = it.isNotBlank()
                        )
                    )
                }
        }
    }

    fun setSearchQuery(query: String) {
        Log.d("SearchViewModel", "setSearchQuery=$query")
        searchQueryFlow.tryEmit(query)
    }

    class Factory(
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchViewModel() as T
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 500L
    }
}