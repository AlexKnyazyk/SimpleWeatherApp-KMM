package com.simple.weather.app.android.presentation.model

sealed class UiState<T> {
    data class Loading<T>(val pullToRefresh: Boolean) : UiState<T>()
    data class Data<T>(val value: T) : UiState<T>()
    data class Error<T>(val uiError: Throwable) : UiState<T>()

    companion object {

        fun <T> loading(pullToRefresh: Boolean = false) = Loading<T>(pullToRefresh)

        fun <T> error(uiError: Throwable) = Error<T>(uiError)
    }
}