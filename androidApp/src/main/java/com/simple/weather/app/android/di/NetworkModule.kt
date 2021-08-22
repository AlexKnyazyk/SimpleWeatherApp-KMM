package com.simple.weather.app.android.di

import com.simple.weather.app.android.presentation.ui.home.HomeViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val networkModule: DI.Module get() = DI.Module("networkModule") {

    bindSingleton { httpClient }

    bindProvider { HomeViewModel.Factory(instance()) }
}

private val httpClient: HttpClient get() = HttpClient(Android) {
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
