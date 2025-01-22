package Kotlin

fun main(){
    val transport = listOf(Car(), Bike())
    transport.forEach{ it.move() }
}

abstract class Transport {
    abstract fun move()
}

class Car : Transport() {
    override fun move() {
        println("Car is driving")
    }
}

class Bike : Transport() {
    override fun move() {
        println("Bike is riding")
    }
}