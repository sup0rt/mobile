package Kotlin.dateTime

import kotlin.system.measureNanoTime

fun main(){
    val i = 1000000
    val time = timer{
        var sum = 0
        for (j in 0..i){
            sum+=1
        }
    }

    println("for ${i} iterations needed ${time/1000000} miliseconds")
}

fun timer(action: () -> Unit): Long {
    return measureNanoTime(action)
}
