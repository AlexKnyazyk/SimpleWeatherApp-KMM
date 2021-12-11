package com.simple.weather.app.android.presentation.ui.search.model

import com.simple.weather.app.domain.domain.model.SearchLocationModel

sealed class SearchLocationResult {

    data class Data(
        val itemModels: List<SearchLocationModel>,
        val hasSearchQuery: Boolean
    ) : SearchLocationResult()

    data class Error(val error: Throwable) : SearchLocationResult()

    companion object {
        val EMPTY = Data(emptyList(), hasSearchQuery = false)
    }
}