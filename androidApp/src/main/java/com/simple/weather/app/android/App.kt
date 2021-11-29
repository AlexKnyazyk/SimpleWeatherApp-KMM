package com.simple.weather.app.android

import android.app.Application
import android.content.Context
import com.simple.weather.app.android.di.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.bindSingleton

class App : Application(), DIAware {

    override val di: DI by DI.lazy {
        bindSingleton<Context> { this@App}
        import(networkModule)
        import(repositoriesModule)
        import(dataModule)
        import(domainModule)
        import(viewModelsModule)
    }
}