package Kotlin.dateTime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

fun main(){
    val year = LocalDate.now().year
    val month = LocalDate.now().monthValue

    printCalendar(year, month)
}

fun printCalendar(year: Int, month: Int){
    val yearMonth=YearMonth.of(year, month)
    val days = yearMonth.lengthOfMonth()

    println("Calendar for ${yearMonth.month} $year")

    for (day in 1..days){
        val date = LocalDate.of(year, month, day)
        val isWeekend = date.dayOfWeek == DayOfWeek.SUNDAY||date.dayOfWeek == DayOfWeek.SATURDAY
        val dayType = if(isWeekend)"Weekend" else "Weekday"

        println("$date : $dayType")
    }
}