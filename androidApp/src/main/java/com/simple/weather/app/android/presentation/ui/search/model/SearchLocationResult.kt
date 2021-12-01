package com.simple.weather.app.android.presentation.ui.search.model

import com.simple.weather.app.android.domain.model.SearchLocationModel

data class SearchLocationResult(
    val itemModels: List<SearchLocationModel>,
    val hasSearchQuery: Boolean
) {

    companion object {
        val EMPTY = SearchLocationResult(emptyList(), hasSearchQuery = false)
    }
}