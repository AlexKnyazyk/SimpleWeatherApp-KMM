package com.simple.weather.app.data.model.response

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val error: Error?
) {

    @Serializable
    data class Error(
        val code: Int,
        val message: String?
    )
}
