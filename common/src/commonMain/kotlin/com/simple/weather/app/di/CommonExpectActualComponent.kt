package com.simple.weather.app.di

import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.features.logging.Logger
import org.koin.core.module.Module

internal expect val commonExpectActualModule: Module

internal expect class SqliteDriverFactory {
    fun createDriver(): SqlDriver
}

internal expect class HttpClientEngineFactoryProvider {
    fun getHttpClientEngineFactory(): HttpClientEngineFactory<*>
}

internal expect class HttpClientLogger() : Logger