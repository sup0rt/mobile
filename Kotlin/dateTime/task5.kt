package Kotlin.dateTime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

fun main(){
    val month=LocalDate.now().monthValue
    val year=LocalDate.now().year

    println(weekendCounter(year, month))
}

fun weekendCounter(year: Int, month: Int): Int {
    val yearMonth = YearMonth.of(year, month)
    val daysInMonth = yearMonth.lengthOfMonth()
    var weekendDays = 0

    for (day in 1..daysInMonth) {
        val date = LocalDate.of(year, month, day)
        if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
            weekendDays++
        }
    }
    return weekendDays
}
