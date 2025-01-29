package Kotlin.dateTime

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

fun main(){
    val utcDate = Instant.now()
    println("UTC time : $utcDate")

    println(converter(utcDate))
}

fun converter(utcDate: Instant): String{
    val moscowZone = ZoneId.of("Europe/Moscow")
    val moscowTime = ZonedDateTime.ofInstant(utcDate, moscowZone)

    return "Moscow time: ${moscowTime}"
}