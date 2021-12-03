package com.simple.weather.app.di

import android.content.Context
import com.simple.weather.app.data.PreferencesManager
import com.simple.weather.app.data.db.AppDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal actual val commonExpectActualModule = module {
    factory { PreferencesManager(androidContext()) }
    factory { HttpClientEngineFactoryProvider() }
    factory { SqliteDriverFactory(androidContext()) }
}

internal actual class HttpClientEngineFactoryProvider {
    actual fun getHttpClientEngineFactory(): HttpClientEngineFactory<*> = Android
}

internal actual class SqliteDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "SimpleWeatherApp.db")
    }
}