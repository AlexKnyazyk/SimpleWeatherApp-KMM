package com.simple.weather.app.android.di

import com.simple.weather.app.android.presentation.ui.home.HomeViewModel
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val viewModelsModule: DI.Module
    get() = DI.Module("viewModelsModule") {
        bindProvider { HomeViewModel.Factory(instance(), instance()) }
    }