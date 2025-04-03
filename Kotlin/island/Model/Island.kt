package Kotlin.island.Model

import Kotlin.island.Animals.Predators.*
import Kotlin.island.Animals.Herbrivores.*
import Kotlin.island.Animals.Predator
import Kotlin.island.Animals.Herbrivore
import Kotlin.island.Model.Direction
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ScheduledThreadPoolExecutor
import java.util.concurrent.TimeUnit
import kotlin.math.min
import kotlin.random.Random


class Island(val width: Int, val height: Int) {
    val cells = Array(height) { Array(width) { Cell() } }
    private val scheduledPool = ScheduledThreadPoolExecutor(1)
    private val taskPool = ForkJoinPool.commonPool()
    private var simulationRunning = true

    fun initialize() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                cells[y][x].plants = Random.nextInt(101)
                if (Random.nextDouble() < 0.2) cells[y][x].addPredator(Wolf())
                if (Random.nextDouble() < 0.2) cells[y][x].addPredator(Boa())
                if (Random.nextDouble() < 0.2) cells[y][x].addPredator(Bear())
                if (Random.nextDouble() < 0.2) cells[y][x].addPredator(Fox())
                if (Random.nextDouble() < 0.2) cells[y][x].addPredator(Eagle())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Horse())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Deer())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Mouse())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Goat())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Sheep())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Boar())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Buffalo())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Duck())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Caterpillar())
                if (Random.nextDouble() < 0.1) cells[y][x].addHerbivore(Rabbit())
            }
        }
    }

    private fun growPlants() {
        processCells { cell ->
            if (cell.plants < 200) {
                cell.plants = min(200, cell.plants + Random.nextInt(1, 10))
            }
        }
    }

    private fun processCells(action: (Cell) -> Unit) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                action(cells[y][x])
            }
        }
    }

    fun getValidPosition(position: Pair<Int, Int>, direction: Direction): Pair<Int, Int> {
        var (x, y) = position
        when (direction) {
            Direction.UP -> y = (y - 1).coerceAtLeast(0)
            Direction.DOWN -> y = (y + 1).coerceAtMost(height - 1)
            Direction.LEFT -> x = (x - 1).coerceAtLeast(0)
            Direction.RIGHT -> x = (x + 1).coerceAtMost(width - 1)
        }
        return Pair(x, y)
    }

    fun moveAnimal(animal: Kotlin.island.Animals.Animal, from: Pair<Int, Int>, to: Pair<Int, Int>) {
        if (from == to) return
        val fromCell = cells[from.second][from.first]
        val toCell = cells[to.second][to.first]
        synchronized(fromCell) {
            synchronized(toCell) {
                when (animal) {
                    is Predator -> {
                        if (fromCell.predators.remove(animal)) {
                            if (toCell.predators.size < animal.maxCountPerCell) {
                                toCell.addPredator(animal)
                            } else {
                                fromCell.addPredator(animal)
                            }
                        }
                    }
                    is Herbrivore -> {
                        if (fromCell.herbivores.remove(animal)) {
                            if (toCell.herbivores.size < animal.maxCountPerCell) {
                                toCell.addHerbivore(animal)
                            } else {
                                fromCell.addHerbivore(animal)
                            }
                        }
                    }
                }
            }
        }
    }

    fun startSimulation(updateUI: () -> Unit) {
        scheduledPool.scheduleAtFixedRate({
            taskPool.execute {
                if (!simulationRunning) return@execute
                try {
                    growPlants()
                    processCells { cell ->
                        synchronized(cell){
                            cell.predators.toList().forEach {
                                it.eat(cell)
                            }
                            cell.herbivores.toList().forEach {
                                it.eat(cell)
                            }
                        }
                    }
                    processCells { cell ->
                        val y = cells.indexOfFirst { row -> row.contains(cell) }
                        val x = cells[y].indexOf(cell)
                        cell.predators.toList().forEach { it.move(this, Pair(x, y)) }
                        cell.herbivores.toList().forEach { it.move(this, Pair(x, y)) }
                    }
                    processCells { cell ->
                        cell.predators.toList().forEach {
                            if(it.satiety <= 0) {
                                it.die(cell)
                            }
                        }
                        cell.herbivores.toList().forEach {
                            if(it.satiety <= 0) {
                                it.die(cell)
                            }
                        }
                    }
                    processCells { cell ->
                        cell.predators.forEach {
                            it.reproduce(cell)
                        }
                        cell.herbivores.forEach {
                            it.reproduce(cell)
                        }
                    }


                    val totalPredators = cells.sumOf { row -> row.sumOf { cell -> cell.predators.size } }
                    val totalHerbivores = cells.sumOf { row -> row.sumOf { cell -> cell.herbivores.size } }
                    println("Total Predators: $totalPredators, Total Herbivores: $totalHerbivores")

                    if (totalPredators == 0 && totalHerbivores == 0) {
                        println("All animals are dead. Stopping simulation.")
                        stopSimulation()
                        simulationRunning = false
                    }

                    updateUI()

                } catch (e: Exception) {
                    println("Exception during simulation step: ${e.message}")
                    e.printStackTrace()
                }
            }
        }, 0, 1, TimeUnit.SECONDS)
    }


    private fun stopSimulation() {
        scheduledPool.shutdown()
        taskPool.shutdown()
        scheduledPool.awaitTermination(5, TimeUnit.SECONDS)
        taskPool.awaitTermination(5, TimeUnit.SECONDS)
    }
}