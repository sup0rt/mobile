package Kotlin.dateTime

import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.coroutines.coroutineContext

fun main(){
    val date = LocalDate.now().plusDays(3)
    val time = LocalTime.of(16, 30,0)

    println(getTime(date, time))
}

fun getTime(date: LocalDate, time: LocalTime): String{
    val currentDateTime = LocalDateTime.now()
    val dateTime = LocalDateTime.of(date, time)

    if (dateTime.isAfter(currentDateTime)) date.plusDays(1)

    val duration = Duration.between(currentDateTime, dateTime)
    val h = duration.toHours()
    val m = duration.minusHours(h).toMinutes()
    val s = duration.minusHours(h).minusMinutes(m).toSeconds()

    return "$h hours, $m minutes, $s seconds left till your event"
}