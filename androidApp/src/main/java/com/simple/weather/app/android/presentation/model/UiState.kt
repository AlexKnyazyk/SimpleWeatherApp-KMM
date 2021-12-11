package com.simple.weather.app.android.presentation.model

sealed class UiState<T> {
    class Loading<T> : UiState<T>()
    data class Data<T>(val value: T) : UiState<T>()
    data class Error<T>(val error: Throwable) : UiState<T>()

    companion object {

        fun <T> loading() = Loading<T>()

        fun <T> data(value: T) = Data(value)

        fun <T> error(uiError: Throwable) = Error<T>(uiError)
    }
}

fun <T> UiState<T>.asData() = this as? UiState.Data<T>