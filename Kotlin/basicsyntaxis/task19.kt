package Kotlin.basicsyntaxis

fun main() {
    val smartphone = Smartphone("Samsung")
    smartphone.turnOn()
    smartphone.takePhoto()
    smartphone.turnOff()

    val laptop = Laptop("Dell")
    laptop.turnOn()
    laptop.turnOff()
}

open class Device(val brand: String) {
    fun turnOn() {
        println("$brand device is turned on")
    }

    fun turnOff() {
        println("$brand device is turned off")
    }
}

class Smartphone(brand: String) : Device(brand) {
    fun takePhoto() {
        println("$brand smartphone takes a photo")
    }
}

class Laptop(brand: String) : Device(brand)