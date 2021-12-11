package com.simple.weather.app.android.di

import com.simple.weather.app.android.presentation.ui.details.LocationWeatherViewModel
import com.simple.weather.app.android.presentation.ui.favorites.FavoritesViewModel
import com.simple.weather.app.android.presentation.ui.home.HomeViewModel
import com.simple.weather.app.android.presentation.ui.search.SearchViewModel
import com.simple.weather.app.android.presentation.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel(get(), get(), get()) }
    viewModel { FavoritesViewModel(get(), get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { (id: Int) -> LocationWeatherViewModel(id, get(), get()) }
    viewModel { SettingsViewModel(get()) }
}