package com.simple.weather.app.android

import android.app.Application
import com.simple.weather.app.android.di.networkModule
import com.simple.weather.app.android.di.repositoriesModule
import org.kodein.di.DI
import org.kodein.di.DIAware

class App : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(networkModule)
        import(repositoriesModule)
    }
}