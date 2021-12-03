package com.simple.weather.app.utils

import java.util.*

actual typealias DateTimestamp = Date

actual fun dateTimestampNow(): DateTimestamp = Date()

actual fun Long.toDateTimestamp(): DateTimestamp {
    val time = this
    return Date(time)
}

actual fun DateTimestamp.time(): Long = this.time

actual fun DateTimestamp.isSameDay(another: DateTimestamp): Boolean {
    val currentCalendar = this.toCalendar()
    val anotherCalendar = another.toCalendar()
    return currentCalendar[Calendar.YEAR] == anotherCalendar[Calendar.YEAR]
            && currentCalendar[Calendar.DAY_OF_YEAR] == anotherCalendar[Calendar.DAY_OF_YEAR]
}

private fun Date.toCalendar() = Calendar.getInstance().apply { time = this@toCalendar }