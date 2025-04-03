package Kotlin.island.Model

import Kotlin.island.Animals.Animal
import Kotlin.island.Animals.Predator
import Kotlin.island.Animals.Herbrivore
import java.util.concurrent.CopyOnWriteArrayList

data class Cell(
    var plants: Int = 0,
    val predators: MutableList<Predator> = CopyOnWriteArrayList(),
    val herbivores: MutableList<Herbrivore> = CopyOnWriteArrayList()
) {
    fun removeAnimal(animal: Animal) {
        when (animal) {
            is Herbrivore -> herbivores.remove(animal)
            is Predator -> predators.remove(animal)
        }
    }

    fun eatHerbrivore(herbivore: Herbrivore) {
        synchronized(herbivores) {
            herbivores.remove(herbivore)
        }
    }

    fun addPredator(predator: Predator) {
        synchronized(predators) {
            if (predators.size < predator.maxCountPerCell) {
                predators.add(predator)
            }
        }
    }

    fun addHerbivore(herbivore: Herbrivore) {
        synchronized(herbivores) {
            if (herbivores.size < herbivore.maxCountPerCell) {
                herbivores.add(herbivore)
            }
        }
    }
}