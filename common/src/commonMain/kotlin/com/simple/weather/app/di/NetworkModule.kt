package com.simple.weather.app.di

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
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