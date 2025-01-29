package Kotlin.dateTime

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.random.Random

fun main(){
    val start = LocalDate.now().minusYears(1)
    val end = LocalDate.now()

    println(genRandDate(start, end))
}

fun genRandDate(start: LocalDate, end: LocalDate): LocalDate{
    val random = Random.Default
    val days = ChronoUnit.DAYS.between(start, end)
    val randDays = random.nextLong(days + 1)

    return start.plusDays(randDays)
}