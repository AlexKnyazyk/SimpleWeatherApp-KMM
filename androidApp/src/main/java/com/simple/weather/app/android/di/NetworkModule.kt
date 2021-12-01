package com.simple.weather.app.android.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.parameter
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { httpClient }
}

private val httpClient: HttpClient
    get() = HttpClient(Android) {
        install(HttpTimeout) {
            requestTimeoutMillis = TimeUnit.SECONDS.toMillis(20)
        }

        install(Logging) {
            logger = CustomAndroidHttpLogger
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

private object CustomAndroidHttpLogger : Logger {
    private const val logTag = "CustomHttpLogger"

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}