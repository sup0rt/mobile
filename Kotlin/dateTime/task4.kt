package Kotlin.dateTime

import java.time.LocalDate
import java.time.Year

fun main(){
    val year = LocalDate.now().year

    println(isLeap(year))
}

fun isLeap(year: Int): Boolean {
    return Year.of(year).isLeap
}