package com.simple.weather.app.android.presentation.model

import com.simple.weather.app.android.domain.model.SearchLocationModel

data class SearchLocationResult(
    val itemModels: List<SearchLocationModel>,
    val hasSearchQuery: Boolean
)