package Kotlin.singletonEnum


object Logger {
    private val logs = mutableListOf<String>()

    fun log(message: String) {
        logs.add(message)
    }

    fun printLogs() {
        println("Logs:")
        logs.forEach { println(it) }
    }

    fun clearLogs() {
        logs.clear()
    }
}

fun main() {
    Logger.log("1")
    Logger.log("1")
    Logger.log("3")
    Logger.log("4")

    Logger.printLogs()
    Logger.clearLogs()
    Logger.printLogs()

    val logger1 = Logger
    val logger2 = Logger
    if(logger1 === logger2) {
        println("Both logger1 and logger2 are referencing the same object")
    } else {
        println("logger1 and logger2 are not referencing the same object")
    }
}