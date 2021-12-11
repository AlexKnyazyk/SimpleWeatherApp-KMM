package com.simple.weather.app.android

import android.app.Application
import com.simple.weather.app.android.utils.location.DeviceLocationManagerImpl
import com.simple.weather.app.android.di.*
import com.simple.weather.app.di.commonDiModules
import com.simple.weather.app.utils.DeviceLocationManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(module {
                single<DeviceLocationManager> { DeviceLocationManagerImpl(androidContext()) }
            })
            modules(commonDiModules)
            modules(
                viewModelsModule
            )
        }
    }
}