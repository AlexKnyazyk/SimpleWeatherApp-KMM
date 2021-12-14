package com.simple.weather.app.android.presentation.ui.error

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.simple.weather.app.android.R
import com.simple.weather.app.domain.domain.model.errors.NoInternetConnectionError
import com.simple.weather.app.domain.domain.model.errors.ServerError

@Composable
fun Throwable?.toUiErrorMessage() = when (this) {
    is NoInternetConnectionError -> stringResource(R.string.error_network)
    is ServerError -> message?.let { stringResource(R.string.error_generic_format, code, it) } ?: stringResource(R.string.error_unknown)
    else -> stringResource(R.string.error_unknown)
}