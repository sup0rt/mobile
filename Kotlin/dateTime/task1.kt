package Kotlin.dateTime

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun main(){
    val date = LocalDate.now()
    val time = LocalTime.now()
    val dateFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val timeFormater = DateTimeFormatter.ofPattern("HH:mm:ss")

    val currentTime = time.format(timeFormater)
    val currentDate = date.format(dateFormater)

    println("Current date is: $currentDate")
    println("Current time is: $currentTime")
}