package com.simple.weather.app.domain.model.errors

class ServerError(val code: Int, message: String?) : Throwable(message)