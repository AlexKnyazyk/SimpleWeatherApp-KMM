package com.simple.weather.app.android.utils

import java.util.Calendar

fun Long.toCalendar() = Calendar.getInstance().apply {
    timeInMillis = this@toCalendar
}

fun Calendar.isSameDay(other: Calendar): Boolean {
    return this[Calendar.YEAR] == other[Calendar.YEAR]
            && this[Calendar.DAY_OF_YEAR] == other[Calendar.DAY_OF_YEAR]
}