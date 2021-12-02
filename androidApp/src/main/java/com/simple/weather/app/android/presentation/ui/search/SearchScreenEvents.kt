package com.simple.weather.app.android.presentation.ui.search

sealed interface SearchScreenEvents {
    object NavigateBack : SearchScreenEvents
    object ExistedFavoriteMessage : SearchScreenEvents
}