package com.simple.weather.app.android.presentation.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationItemUi
import com.simple.weather.app.android.presentation.ui.favorites.model.toUi
import com.simple.weather.app.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.domain.repository.SettingsRepository
import com.simple.weather.app.domain.usecase.favorites.ISyncFavoriteLocationsWeatherUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoriteLocationsRepository: FavoriteLocationsRepository,
    settingsRepository: SettingsRepository,
    private val syncFavoriteLocationsWeatherUseCase: ISyncFavoriteLocationsWeatherUseCase
) : ViewModel() {

    val favoriteLocations: StateFlow<List<FavoriteLocationItemUi>> = combine(
        favoriteLocationsRepository.allFavoriteLocations(),
        settingsRepository.settingsUnitsModelFlow
    ) { favList, settings ->
        favList.map { it.toUi(settings.isTempMetric) }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            syncFavoriteLocationsWeatherUseCase()
        }
    }

    fun deleteFavorite(model: FavoriteLocationItemUi) {
        viewModelScope.launch {
            favoriteLocationsRepository.deleteById(model.id)
        }
    }
}