package com.simple.weather.app.di

import com.simple.weather.app.data.PreferencesManager
import com.simple.weather.app.data.db.AppDatabase
import com.simple.weather.app.data.repository.location.DeviceLocationRepositoryImpl
import com.simple.weather.app.domain.repository.DeviceLocationRepository
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.ios.Ios
import io.ktor.client.features.logging.Logger
import org.koin.dsl.module
import platform.Foundation.NSLog

actual val commonExpectActualModule = module {
    factory { PreferencesManager() }
    factory { HttpClientEngineFactoryProvider() }
    factory { SqliteDriverFactory() }
    single<DeviceLocationRepository> { DeviceLocationRepositoryImpl() }
}

internal actual class HttpClientEngineFactoryProvider {
    actual fun getHttpClientEngineFactory(): HttpClientEngineFactory<*> = Ios
}

internal actual class HttpClientLogger : Logger {
    override fun log(message: String) {
        NSLog("$TAG: $message")
    }

    companion object {
        private const val TAG = "HttpClientLogger"
    }
}

internal actual class SqliteDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(AppDatabase.Schema, "SimpleWeatherApp.db")
    }
}