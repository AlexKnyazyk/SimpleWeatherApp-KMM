package com.simple.weather.app.utils

import kotlin.coroutines.CoroutineContext

expect val defaultDispatcher: CoroutineContext

expect val uiDispatcher: CoroutineContext