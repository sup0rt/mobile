package Kotlin.basicsyntaxis

fun main(){
    val animals = listOf(Dog(), Cat(), Cow())
    animals.forEach { it.makeSound() }
}

interface Animal {
    fun makeSound()
}

class Dog : Animal {
    override fun makeSound() {
        println("Woof!")
    }
}

class Cat : Animal {
    override fun makeSound() {
        println("Meow!")
    }
}

class Cow : Animal {
    override fun makeSound() {
        println("Moo!")
    }
}