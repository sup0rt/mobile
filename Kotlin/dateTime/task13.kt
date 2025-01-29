package Kotlin.dateTime

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun calculateWorkingHours(start: LocalDateTime, end: LocalDateTime): Long {
    var totalWorkingHours = 0L
    var currentDate = start

    while (currentDate.isBefore(end) || currentDate.isEqual(end)) {
        val dayOfWeek = currentDate.dayOfWeek
        if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
            val nextDay = currentDate.plusDays(1)
            val hoursInDay = if(nextDay.isAfter(end)) {
                ChronoUnit.HOURS.between(currentDate, end)
            } else {
                24
            }
            totalWorkingHours += hoursInDay.coerceAtLeast(0) // Ensure non-negative hours
        }
        currentDate = currentDate.plusDays(1)
    }

    return totalWorkingHours
}

fun main() {
    val start = LocalDateTime.of(2024, 1, 1, 9, 0) // March 1st, 9 AM
    val end = LocalDateTime.of(2024, 1, 8, 17, 0)  // March 8th, 5 PM

    println(calculateWorkingHours(start, end))
}