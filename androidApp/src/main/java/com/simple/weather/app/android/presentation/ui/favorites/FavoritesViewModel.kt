package com.simple.weather.app.android.presentation.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.domain.model.FavoriteLocationModel
import com.simple.weather.app.android.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.android.domain.usecase.ISyncFavoriteLocationsWeatherUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoriteLocationsRepository: FavoriteLocationsRepository,
    private val syncFavoriteLocationsWeatherUseCase: ISyncFavoriteLocationsWeatherUseCase
) : ViewModel() {

    val favoriteLocations: StateFlow<List<FavoriteLocationModel>> =
        favoriteLocationsRepository.allFavoriteLocations()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            syncFavoriteLocationsWeatherUseCase()
        }
    }
}