package Kotlin.dateTime

import java.time.LocalDate

fun main(){
    val firstDate = LocalDate.now()
    val secondDate = LocalDate.now().minusYears(1)

    println(dateCopmararor(firstDate, secondDate))
}

fun dateCopmararor(first: LocalDate, second: LocalDate): String {
    return when{
        first.isBefore(second)->"date ${first} is before date ${second}"
        first.isAfter(second)->"date ${second} is before date ${first}"
        else ->"dates ${first} and ${second} are equal"
    }
}