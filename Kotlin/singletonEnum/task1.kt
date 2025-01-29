package Kotlin.singletonEnum

object DatabaseConnection {
    init {
        println("connecting ")
    }

    fun connect() {
        println("connection success")
    }
}


fun main() {
    val connection1 = DatabaseConnection
    val connection2 = DatabaseConnection
    println(connection1)
    println(connection2)


    connection1.connect()
    connection2.connect()

    if(connection1 === connection2) {
        println("connection1 and connection2 are referencing the same object")
    } else {
        println("connection1 and connection2 are not referencing the same object")
    }
}