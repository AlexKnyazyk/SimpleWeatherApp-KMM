package com.simple.weather.app.android.presentation.ui.search.model

data class SearchLocationResult(
    val itemModels: List<SearchItemUi>,
    val hasSearchQuery: Boolean
) {

    companion object {
        val EMPTY = SearchLocationResult(emptyList(), hasSearchQuery = false)
    }
}