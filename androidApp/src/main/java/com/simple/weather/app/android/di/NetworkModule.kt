package com.simple.weather.app.android.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.parameter
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val networkModule: DI.Module
    get() = DI.Module("networkModule") {
        bindSingleton { httpClient }
    }

private val httpClient: HttpClient
    get() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
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
