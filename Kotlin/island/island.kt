package Kotlin.island

import java.util.concurrent.*
import javax.swing.*
import java.awt.*
import java.util.concurrent.*
import kotlin.math.ceil
import kotlin.random.Random
import kotlin.math.min
import kotlin.math.max

abstract class Animal(
    val symbol: String,
    val maxCountPerCell: Int,
    val speed: Int,
    val foodNeeded: Double
) {
    var satiety: Double = foodNeeded
    open val foodValue: Double = 0.0

    abstract fun eat(cell: Cell)
    abstract fun move(island: Island, currentPosition: Pair<Int, Int>)
    abstract fun reproduce(cell: Cell)

    fun die(cell: Cell) {
        cell.removeAnimal(this)
    }
}

abstract class Predator(symbol: String, maxCountPerCell: Int, speed: Int, foodNeeded: Double) : Animal(symbol, maxCountPerCell, speed, foodNeeded)

abstract class Herbivore(symbol: String, maxCountPerCell: Int, speed: Int, foodNeeded: Double) : Animal(symbol, maxCountPerCell, speed, foodNeeded)

class Wolf : Predator("🐺", 30, 3, 8.0) {
    override val foodValue: Double = 8.0

    override fun eat(cell: Cell) {
        val herbrivore = cell.herbivores.firstOrNull { it is Herbivore }
        if (herbrivore != null && Random.nextInt(100) < 70) {
            satiety = min(satiety + herbrivore.foodValue, foodNeeded)
            cell.eatHerbrivore(herbrivore)
        }
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.predators.count { it is Wolf } >= 2 && Random.nextInt(100) < 15 && cell.predators.size < maxCountPerCell) {
            cell.addPredator(Wolf())
        }
    }
}

class Boa : Predator("🐍", 30, 1, 3.0) {
    override val foodValue: Double = 3.0

    override fun eat(cell: Cell) {
        val herbrivore = cell.herbivores.firstOrNull { it is Herbivore }
        if (herbrivore != null && Random.nextInt(100) < 70) {
            satiety = min(satiety + herbrivore.foodValue, foodNeeded)
            cell.eatHerbrivore(herbrivore)
        }
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.predators.count { it is Boa } >= 2 && Random.nextInt(100) < 15 && cell.predators.size < maxCountPerCell) {
            cell.addPredator(Boa())
        }
    }
}

class Fox : Predator("🦊", 30, 2, 2.0) {
    override val foodValue: Double = 2.0

    override fun eat(cell: Cell) {
        val herbrivore = cell.herbivores.firstOrNull { it is Herbivore }
        if (herbrivore != null && Random.nextInt(100) < 70) {
            satiety = min(satiety + herbrivore.foodValue, foodNeeded)
            cell.eatHerbrivore(herbrivore)
        }
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.predators.count { it is Fox } >= 2 && Random.nextInt(100) < 15 && cell.predators.size < maxCountPerCell) {
            cell.addPredator(Fox())
        }
    }
}

class Bear : Predator("🐻", 5, 2, 80.0) {
    override val foodValue: Double = 80.0

    override fun eat(cell: Cell) {
        val herbrivore = cell.herbivores.firstOrNull { it is Herbivore }
        if (herbrivore != null && Random.nextInt(100) < 70) {
            satiety = min(satiety + herbrivore.foodValue, foodNeeded)
            cell.eatHerbrivore(herbrivore)
        }
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.predators.count { it is Bear } >= 2 && Random.nextInt(100) < 15 && cell.predators.size < maxCountPerCell) {
            cell.addPredator(Bear())
        }
    }
}

class Eagle : Predator("🦅", 20, 3, 1.0) {
    override val foodValue: Double = 1.0

    override fun eat(cell: Cell) {
        val herbrivore = cell.herbivores.firstOrNull { it is Herbivore }
        if (herbrivore != null && Random.nextInt(100) < 70) {
            satiety = min(satiety + herbrivore.foodValue, foodNeeded)
            cell.eatHerbrivore(herbrivore)
        }
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.predators.count { it is Eagle } >= 2 && Random.nextInt(100) < 15 && cell.predators.size < maxCountPerCell) {
            cell.addPredator(Eagle())
        }
    }
}

class Horse : Herbivore("🐎", 20, 4, 60.0) {
    override val foodValue: Double = 60.0

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Horse } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Horse())
        }
    }
}

class Deer : Herbivore("🦌", 20, 4, 50.0) {
    override val foodValue: Double = 50.0

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Deer } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Deer())
        }
    }
}

class Rabbit : Herbivore("🐇", 150, 2, 0.45) {
    override val foodValue: Double = 0.45

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Rabbit } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Rabbit())
        }
    }
}

class Mouse : Herbivore("🐭", 500, 1, 0.01) {
    override val foodValue: Double = 0.01

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Mouse } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Mouse())
        }
    }
}

class Goat : Herbivore("🐐", 140, 3, 10.0) {
    override val foodValue: Double = 10.0

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Goat } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Goat())
        }
    }
}

class Sheep : Herbivore("🐑", 140, 3, 15.0) {
    override val foodValue: Double = 15.0

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Sheep } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Sheep())
        }
    }
}

class Boar : Herbivore("🐗", 50, 2, 50.0) {
    override val foodValue: Double = 50.0

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Boar } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Boar())
        }
    }
}

class Buffalo : Herbivore("🐃", 10, 3, 100.0) {
    override val foodValue: Double = 100.0

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Buffalo } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Buffalo())
        }
    }
}

class Duck : Herbivore("🦆", 200, 4, 0.15) {
    override val foodValue: Double = 0.15

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed)
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Duck } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Duck())
        }
    }
}

class Caterpillar : Herbivore("🐛", 1000, 0, 0.0) {
    override val foodValue: Double = 0.0

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)
        satiety = min(satiety + plantsToEat, foodNeeded)
        cell.plants -= plantsToEat.toInt()
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        // Гусеницы не двигаются
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Caterpillar } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Caterpillar())
        }
    }
}

data class Cell(
    var plants: Int = 0,
    val predators: MutableList<Predator> = CopyOnWriteArrayList(),
    val herbivores: MutableList<Herbivore> = CopyOnWriteArrayList()
) {
    fun removeAnimal(animal: Animal) {
        synchronized(animal){
            when (animal) {
                is Predator -> predators.remove(animal)
                is Herbivore -> herbivores.remove(animal)
            }
        }
    }

    fun eatHerbrivore(animal: Animal) {
        synchronized(animal){
            when (animal) {
                is Herbivore -> herbivores.remove(animal)
            }
        }
    }

    fun addPredator(predator: Predator) {
        synchronized(predator){
            if (predators.size < predator.maxCountPerCell) {
                predators.add(predator)
            }
        }
    }

    fun addHerbivore(herbivore: Herbivore) {
        synchronized(herbivore){
            if (herbivores.size < herbivore.maxCountPerCell) {
                herbivores.add(herbivore)
            }
        }
    }
}

class Island(val width: Int, val height: Int) {
    val cells = Array(height) { Array(width) { Cell() } }
    private val scheduledPool = ScheduledThreadPoolExecutor(1)
    private val taskPool = ForkJoinPool.commonPool()

    fun initialize() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                cells[y][x].plants = Random.nextInt(101)
                if (Random.nextDouble() < 0.1) cells[y][x].addPredator(Wolf())
                if (Random.nextDouble() < 0.1) cells[y][x].addPredator(Boa())
                if (Random.nextDouble() < 0.1) cells[y][x].addPredator(Bear())
                if (Random.nextDouble() < 0.1) cells[y][x].addPredator(Fox())
                if (Random.nextDouble() < 0.1) cells[y][x].addPredator(Eagle())
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

    fun growPlants() {
        processCells { cell ->
            if (cell.plants < 200) {
                cell.plants = min(200, cell.plants + Random.nextInt(1, 10))
            }
        }
    }

    fun processCells(action: (Cell) -> Unit) {
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

    fun moveAnimal(animal: Animal, from: Pair<Int, Int>, to: Pair<Int, Int>) {
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
                    is Herbivore -> {
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
                try {
                    growPlants()
                    processCells { cell ->
                        synchronized(cell){
                            cell.predators.toList().forEach { it.eat(cell) }
                            cell.herbivores.toList().forEach { it.eat(cell) }
                        }
                    }
                    processCells { cell ->
                        val y = cells.indexOfFirst { row -> row.contains(cell) }
                        val x = cells[y].indexOf(cell)
                        cell.predators.toList().forEach { it.move(this, Pair(x, y)) }
                        cell.herbivores.toList().forEach { it.move(this, Pair(x, y)) }
                    }
                    processCells { cell ->
                        cell.predators.removeIf { it.satiety <= 0 }
                        cell.herbivores.removeIf { it.satiety <= 0 }
                    }
                    processCells { cell ->
                        cell.predators.forEach { it.reproduce(cell) }
                        cell.herbivores.forEach { it.reproduce(cell) }
                    }

                    // Обновляем графический интерфейс
                    updateUI()

                } catch (e: Exception) {
                    println("Exception during simulation step: ${e.message}")
                    e.printStackTrace()
                }
            }
        }, 0, 1, TimeUnit.SECONDS)
    }


    fun stopSimulation() {
        scheduledPool.shutdown()
        taskPool.shutdown()
        scheduledPool.awaitTermination(5, TimeUnit.SECONDS)
        taskPool.awaitTermination(5, TimeUnit.SECONDS)
    }
}

class IslandPanel(private val island: Island) : JPanel() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val cellWidth = width / island.width
        val cellHeight = height / island.height

        for (y in 0 until island.height) {
            for (x in 0 until island.width) {
                val cell = island.cells[y][x]
                val color = when {
                    cell.predators.isNotEmpty() -> Color.RED
                    cell.herbivores.isNotEmpty() -> Color.GRAY
                    cell.plants > 0 -> Color(34, 139, 34) // Forest green
                    else -> Color.LIGHT_GRAY
                }
                g.color = color
                g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight)

                // Draw symbols for animals
                if (cell.predators.isNotEmpty()) {
                    g.drawString(cell.predators.first().symbol, x * cellWidth + cellWidth / 4, y * cellHeight + cellHeight / 2)
                } else if (cell.herbivores.isNotEmpty()) {
                    g.drawString(cell.herbivores.first().symbol, x * cellWidth + cellWidth / 4, y * cellHeight + cellHeight / 2)
                }
            }
        }
    }
}



enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    companion object {
        fun random(): Direction {
            return values()[Random.nextInt(values().size)]
        }
    }
}

fun createAndShowGUI(island: Island) {
    val frame = JFrame("Island Simulation")
    val islandPanel = IslandPanel(island)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.add(islandPanel)
    frame.setSize(800, 600)
    frame.isVisible = true

    island.startSimulation {
        islandPanel.repaint()
    }
}
fun main() {
    val island = Island(10, 10)
    island.initialize()
    createAndShowGUI(island)
}