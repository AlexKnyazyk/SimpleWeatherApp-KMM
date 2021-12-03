package com.simple.weather.app.android.presentation.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simple.weather.app.android.presentation.ui.favorites.model.FavoriteLocationModelUi
import com.simple.weather.app.domain.domain.repository.FavoriteLocationsRepository
import com.simple.weather.app.domain.domain.repository.SettingsRepository
import com.simple.weather.app.domain.domain.usecase.favorites.ISyncFavoriteLocationsWeatherUseCase
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

    val favoriteLocations: StateFlow<List<FavoriteLocationModelUi>> = combine(
        favoriteLocationsRepository.allFavoriteLocations(),
        settingsRepository.settingsModelFlow
    ) { favList, settings ->
        val isTempMetric = settings.isTempMetric
        favList.map {
            with(it) { FavoriteLocationModelUi(id, name, region, country, tempC, tempF, weatherConditionIconUrl, updateTimestamp, isTempMetric)  }
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        viewModelScope.launch {
            syncFavoriteLocationsWeatherUseCase()
        }
    }

    fun deleteFavorite(model: FavoriteLocationModelUi) {
        viewModelScope.launch {
            favoriteLocationsRepository.deleteById(model.id)
        }
    }
}