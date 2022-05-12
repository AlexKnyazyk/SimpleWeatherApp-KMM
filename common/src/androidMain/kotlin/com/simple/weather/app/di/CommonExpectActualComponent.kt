package com.simple.weather.app.di

import android.content.Context
import android.util.Log
import com.simple.weather.app.data.PreferencesManager
import com.simple.weather.app.data.db.AppDatabase
import com.simple.weather.app.data.repository.location.DeviceLocationRepositoryImpl
import com.simple.weather.app.domain.repository.DeviceLocationRepository
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.logging.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal actual val commonExpectActualModule = module {
    factory { PreferencesManager(androidContext()) }
    factory { HttpClientEngineFactoryProvider() }
    factory { SqliteDriverFactory(androidContext()) }
    singleOf(::DeviceLocationRepositoryImpl) bind DeviceLocationRepository::class
}

internal actual class HttpClientEngineFactoryProvider {
    actual fun getHttpClientEngineFactory(): HttpClientEngineFactory<*> = Android
}

internal actual class HttpClientLogger: Logger {
    override fun log(message: String) {
        Log.i(TAG, message)
    }

    companion object {
        private const val TAG = "HttpClientLogger"
    }
}

internal actual class SqliteDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(AppDatabase.Schema, context, "SimpleWeatherApp.db")
    }
}