package Kotlin.island.Animals

import Kotlin.island.Model.Cell
import kotlin.math.min
import kotlin.random.Random

abstract class Herbrivore(maxCountPerSell: Int, speed: Int, foodNeeded: Double) :
    Animal(maxCountPerSell,speed, foodNeeded){
    override fun eat(cell: Cell){
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun reproduce(cell: Cell) {
        if(cell.herbivores.count{it::class == this::class} >= 2 &&
            Random.nextInt(100)<15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(this::class.java.getDeclaredConstructor().newInstance())
        }
    }
}