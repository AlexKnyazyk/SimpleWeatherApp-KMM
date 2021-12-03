package com.simple.weather.app.android

import android.app.Application
import com.simple.weather.app.android.di.*
import com.simple.weather.app.di.commonDiModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(commonDiModules)
            modules(
                viewModelsModule
            )
        }
    }
}