package com.simple.weather.app.data.model

sealed class CResult<T> {

    data class Success<T>(val value: T) : CResult<T>()
    data class Failure<T>(val error: Throwable) : CResult<T>()

    inline fun <R> fold(
        onSuccess: (value: T) -> R,
        onFailure: (exception: Throwable) -> R
    ): R {
        return when (this) {
            is Success -> onSuccess(this.value)
            is Failure -> onFailure(this.error)
        }
    }

    inline fun resolve(
        onSuccess: (value: T) -> Unit,
        onFailure: (exception: Throwable) -> Unit
    ) = fold(onSuccess, onFailure)

    fun getOrNull(): T? = (this as? Success)?.value

    inline fun onSuccess(onSuccessAction: (T) -> Unit): CResult<T> {
        if (this is Success) onSuccessAction(this.value)
        return this
    }

    inline fun onFailure(onFailureAction: (Throwable) -> Unit): CResult<T> {
        if (this is Failure) onFailureAction(this.error)
        return this
    }

    inline fun <R> map(transform: (value: T) -> R): CResult<R> {
        return when (this) {
            is Success -> Success(transform(this.value))
            is Failure -> Failure(this.error)
        }
    }

    companion object {
        fun <T> success(value: T): CResult<T> = Success(value)
        fun <T> failure(error: Throwable): CResult<T> = Failure(error)
    }
}