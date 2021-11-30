package com.simple.weather.app.android.di

import com.simple.weather.app.android.presentation.ui.details.WeatherDetailsViewModel
import com.simple.weather.app.android.presentation.ui.home.HomeViewModel
import com.simple.weather.app.android.presentation.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { (name: String) -> WeatherDetailsViewModel(name, get()) }
}