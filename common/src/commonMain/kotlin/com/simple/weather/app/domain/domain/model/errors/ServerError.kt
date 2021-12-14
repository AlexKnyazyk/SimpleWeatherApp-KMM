package com.simple.weather.app.domain.domain.model.errors

class ServerError(val code: Int, message: String?) : Throwable(message)