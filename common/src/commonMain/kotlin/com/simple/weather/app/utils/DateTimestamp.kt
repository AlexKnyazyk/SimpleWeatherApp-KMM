package com.simple.weather.app.utils

expect class DateTimestamp private constructor()

expect fun dateTimestampNow(): DateTimestamp

expect fun Long.toDateTimestamp(): DateTimestamp

expect fun DateTimestamp.time(): Long

expect fun DateTimestamp.isSameDay(another: DateTimestamp): Boolean

