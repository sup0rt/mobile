package Kotlin.dateTime

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

fun main(){
    val date = LocalDate.now()

    println(getDay(date))
}

fun getDay(date: LocalDate): String{
    val day = date.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("ru"))

    return "day of week is: $day"
}