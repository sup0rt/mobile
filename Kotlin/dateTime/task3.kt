package Kotlin.dateTime

import java.time.Duration
import java.time.LocalDate

fun main(){
    val currentDate=LocalDate.now()

    println(daysTillNewYear(currentDate))
}

fun daysTillNewYear(date: LocalDate): String {
    val newYear=LocalDate.of(date.year,12,31)

    val days=Duration.between(date.atStartOfDay(), newYear.atStartOfDay()).toDays()

    return "days left: ${days}"
}