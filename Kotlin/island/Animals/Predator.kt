package Kotlin.island.Animals

import Kotlin.island.Model.Cell
import kotlin.random.Random
import kotlin.math.min

abstract class Predator(maxCountPerSell: Int, speed: Int, foodNeeded: Double) :
    Animal(maxCountPerSell,speed, foodNeeded){
    override fun eat(cell: Cell){
        val herbrivore = cell.herbivores.firstOrNull{true}
        if(herbrivore != null && Random.nextInt(100) < 70){
            satiety = min(satiety + herbrivore.foodValue, foodNeeded)
            cell.eatHerbrivore(herbrivore)
        }
    }

    override fun reproduce(cell: Cell) {
        if(cell.predators.count{it::class == this::class} >= 2 &&
            Random.nextInt(100)<15 && cell.predators.size < maxCountPerCell) {
                cell.addPredator(this::class.java.getDeclaredConstructor().newInstance())
        }
    }
}