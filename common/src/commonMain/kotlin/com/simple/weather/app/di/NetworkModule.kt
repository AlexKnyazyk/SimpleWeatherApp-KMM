package com.simple.weather.app.di

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.parameter
import org.koin.dsl.module

internal val networkModule = module {
    single { getHttpClient(get()) }
}

private fun getHttpClient(factoryProvider: HttpClientEngineFactoryProvider): HttpClient {
    return HttpClient(factoryProvider.getHttpClientEngineFactory()) {
        install(HttpTimeout) {
            requestTimeoutMillis = 20_000
        }
        install(Logging) {
            logger = HttpClientLogger()
            level = LogLevel.BODY
        }
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
            serializer = KotlinxSerializer(json)
        }

        defaultRequest {
            parameter("key", "55e572ab7a5c4e0e908163124212208")
        }
    }
}