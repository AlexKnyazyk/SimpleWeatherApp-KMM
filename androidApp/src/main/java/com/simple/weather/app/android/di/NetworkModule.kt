package com.simple.weather.app.android.di

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.request.parameter
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import java.util.concurrent.TimeUnit

val networkModule: DI.Module
    get() = DI.Module("networkModule") {
        bindSingleton { httpClient }
    }

private val httpClient: HttpClient
    get() = HttpClient(Android) {
        install(HttpTimeout) {
            requestTimeoutMillis = TimeUnit.SECONDS.toMillis(20)
        }

        install(Logging) {
            logger = CustomAndroidHttpLogger
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

private object CustomAndroidHttpLogger : Logger {
    private const val logTag = "CustomHttpLogger"

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}