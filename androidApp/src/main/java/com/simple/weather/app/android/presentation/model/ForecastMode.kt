package com.simple.weather.app.android.presentation.model

import androidx.annotation.StringRes
import com.simple.weather.app.android.R

enum class ForecastMode(@StringRes val textRes: Int) {
    HOURLY(R.string.hourly),
    DAILY(R.string.daily)
}