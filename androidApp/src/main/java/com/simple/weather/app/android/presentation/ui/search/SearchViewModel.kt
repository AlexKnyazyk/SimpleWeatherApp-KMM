package com.simple.weather.app.android.presentation.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import com.simple.weather.app.android.domain.model.SearchLocationModel
import com.simple.weather.app.android.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.android.domain.usecase.ISearchLocationUseCase
import com.simple.weather.app.android.presentation.model.UiState
import com.simple.weather.app.android.presentation.ui.search.model.SearchLocationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchLocationUseCase: ISearchLocationUseCase,
    private val favoriteLocationsRepository: FavoriteLocationsRepository
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
        viewModelScope.launch {
            val model = FavoriteLocationModel(
                itemModel.id,
                itemModel.name,
                itemModel.region,
                itemModel.country,
                null,
                null,
                null,
                null
            )
            favoriteLocationsRepository.addOrUpdate(model)
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE = 500L
    }
}