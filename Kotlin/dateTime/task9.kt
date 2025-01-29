package Kotlin.dateTime

import java.time.LocalDate
import java.time.Period

fun main(){
    val birthday = LocalDate.of(2000,1,30)

    println(getAge(birthday))
}

fun getAge(birthday: LocalDate): String{
    val currentDate = LocalDate.now()
    val years = Period.between(birthday, currentDate).years

    return "age in years is: ${years}"
}