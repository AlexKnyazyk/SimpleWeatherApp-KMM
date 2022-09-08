package com.simple.weather.app.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
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
        install(ContentNegotiation) {
            json(kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
                isLenient = true
            })
        }
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = "api.weatherapi.com"
                path("v1/")
                parameters.append("key", "55e572ab7a5c4e0e908163124212208")
            }
        }
    }
}