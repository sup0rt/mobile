package Kotlin.Island

fun main() {
    val wolf = Wolf(50f, 3, 10)
    val rabbit = Rabbit(3f, 3, 8)

    wolf.eat(rabbit)
    rabbit.eat(1.0f)
}

abstract class Animal(
    val name: String,
    val type: String,
    var mass: Float,
    val maxAmount: Int,
    var maxSpeed: Int,
    var foodNeeded: Float,
    var currentFood: Float
) {
    var isAlive: Boolean = true

    init {
        currentFood = foodNeeded / 2
    }

    open fun eat(amount: Float) {
        if (isAlive) {
            currentFood += amount
            println("$name eats $amount units of food.")
        } else {
            println("$name is dead and cannot eat.")
        }
    }

    open fun eat(prey: Animal) {
        println("$name cannot eat other animals directly.")
    }

    open fun move() {
        if (isAlive) {
            println("$name moves.")
        } else {
            println("$name is dead and cannot move.")
        }
    }

    open fun reproduce() {
        if (isAlive) {
            println("$name reproduces.")
        } else {
            println("$name is dead and cannot reproduce.")
        }
    }

    open fun die() {
        isAlive = false
        println("$name dies.")
    }
}

abstract class Herbivore(
    name: String,
    mass: Float,
    maxAmount: Int,
    maxSpeed: Int,
    foodNeeded: Float,
    currentFood: Float
) : Animal(name, "Herbivore", mass, maxAmount, maxSpeed, foodNeeded, currentFood) {
    fun beEaten() {
        die()
    }
}

class Rabbit(
    mass: Float,
    maxAmount: Int,
    maxSpeed: Int
) : Herbivore("Rabbit", mass, maxAmount, maxSpeed, 0.45f, 0.1f) {

}

class Wolf(
    mass: Float,
    maxAmount: Int,
    maxSpeed: Int,
) : Animal("Wolf", "Predator", mass, maxAmount, maxSpeed, 8f, 8f) {

    override fun eat(prey: Animal) {
        if (prey is Herbivore && prey.isAlive) {
            if (currentFood < foodNeeded) {
                currentFood -= foodNeeded
                println("$name hunts and eats ${prey.name}.")
                prey.beEaten()
            } else {
                println("$name does not have enough food to hunt.")
            }
        } else {
            println("$name cannot eat that type of prey.")
        }
    }

    override fun move() {
        println("$name runs quickly.")
    }

    override fun reproduce() {
        println("$name howls to attract a mate.")
    }

    override fun die() {
        println("$name succumbs to hunger or injury.")
    }
}