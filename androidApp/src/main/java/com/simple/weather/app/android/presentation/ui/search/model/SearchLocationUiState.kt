package com.simple.weather.app.android.presentation.ui.search.model

import com.simple.weather.app.domain.domain.model.SearchLocationModel

sealed class SearchLocationUiState {

    object Idle : SearchLocationUiState()

    data class Data(
        val itemModels: List<SearchLocationModel>
    ) : SearchLocationUiState()

    data class Error(val error: Throwable) : SearchLocationUiState()
}