package Kotlin.dateTime

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun main(){
    val date = "27-01-2025"

    println(dateParse(date))
}

fun dateParse(inDate: String): String{
    val inDateFormater = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    val outDateFormater = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    val date = LocalDate.parse(inDate, inDateFormater)
    val newDate = date.plusDays(10).format(outDateFormater)

    return "new date is: ${newDate}"
}