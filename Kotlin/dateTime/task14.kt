package Kotlin.dateTime

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

fun main(){
    val date = LocalDate.now()

    println(dateForm(date, Locale("ru")))
    println(dateForm(date, Locale("en")))
}

fun dateForm(date: LocalDate, locale: Locale): String{
    val formater = DateTimeFormatter.ofPattern("dd-MMMM-yyyy", locale)

    return date.format(formater)
}