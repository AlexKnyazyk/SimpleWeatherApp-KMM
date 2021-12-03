package com.simple.weather.app.utils

import platform.Foundation.*

actual typealias DateTimestamp = NSDate

actual fun dateTimestampNow(): DateTimestamp = NSDate()

actual fun Long.toDateTimestamp(): DateTimestamp {
    val time = this
    return NSDate(time.toDouble())
}

actual fun DateTimestamp.time(): Long = this.timeIntervalSinceReferenceDate.toLong()

actual fun DateTimestamp.isSameDay(another: DateTimestamp): Boolean {
    val current = this
    val calendar = NSCalendar.currentCalendar()
    return calendar.component(NSCalendarUnitYear, current) == calendar.component(NSCalendarUnitYear, another)
            && calendar.component(NSCalendarUnitMonth, current) == calendar.component(NSCalendarUnitMonth, another)
            && calendar.component(NSCalendarUnitDay, current) == calendar.component(NSCalendarUnitDay, another)
}