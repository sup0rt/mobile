package Kotlin.island.Animals

import Kotlin.island.Model.Cell
import Kotlin.island.Model.Direction
import Kotlin.island.Model.Island
import kotlin.math.max

abstract class Animal(
    val maxCountPerCell: Int,
    val speed: Int,
    val foodNeeded: Double
) {
    var satiety: Double = foodNeeded
    open val foodValue: Double = 0.0

    open fun eat(cell: Cell){

    }
    open fun move(island: Island, currentPosition: Pair<Int, Int>){
        repeat(speed){
            val direction=Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety=max(0.0, satiety - foodNeeded * 0.1 * speed)
    }
    open fun reproduce(cell: Cell){

    }

    fun die(cell: Cell) {

        synchronized(cell){
            cell.removeAnimal(this)
        }
    }
}