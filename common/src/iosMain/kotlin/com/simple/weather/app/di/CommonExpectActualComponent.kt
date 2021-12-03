package com.simple.weather.app.di

import com.simple.weather.app.data.PreferencesManager
import com.simple.weather.app.data.db.AppDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*
import org.koin.dsl.module

internal actual val commonExpectActualModule = module {
    factory { PreferencesManager() }
    factory { HttpClientEngineFactoryProvider() }
    factory { SqliteDriverFactory() }
}

internal actual class HttpClientEngineFactoryProvider {
    actual fun getHttpClientEngineFactory(): HttpClientEngineFactory<*> = Ios
}

internal actual class SqliteDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "SimpleWeatherApp.db")
    }
}