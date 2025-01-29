package Kotlin.basicsyntaxis

fun mainAnimalMovement() {

    val animal = AnimalMove()
    val fish = Fish()
    val bird = Bird()
    val dog = Dog2()

    animal.move()
    fish.move()
    bird.move()
    dog.move()
}

open class AnimalMove {
    open fun move() {
        println("Animal is moving")
    }
}

class Fish : AnimalMove() {
    override fun move() {
        println("Fish is swimming")
    }
}

class Bird : AnimalMove() {
    override fun move() {
        println("Bird is flying")
    }
}

class Dog2 : AnimalMove() {
    override fun move() {
        println("Dog is running")
    }
}