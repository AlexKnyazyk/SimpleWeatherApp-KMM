package com.simple.weather.app.android

import android.app.Application
import com.simple.weather.app.android.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                networkModule,
                databaseModule,
                repositoriesModule,
                dataModule,
                domainModule,
                viewModelsModule
            )
        }
    }
}