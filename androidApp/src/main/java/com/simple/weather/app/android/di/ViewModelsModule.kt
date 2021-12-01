package com.simple.weather.app.android.di

import com.simple.weather.app.android.presentation.ui.details.WeatherDetailsViewModel
import com.simple.weather.app.android.presentation.ui.favorites.FavoritesViewModel
import com.simple.weather.app.android.presentation.ui.home.HomeViewModel
import com.simple.weather.app.android.presentation.ui.search.SearchViewModel
import com.simple.weather.app.android.presentation.ui.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { (name: String) -> WeatherDetailsViewModel(name, get()) }
    viewModel { SettingsViewModel() }
}