package Kotlin.island

import java.util.concurrent.*
import kotlin.random.Random
import kotlin.math.min
import kotlin.math.max

// Абстрактный класс для всех животных
abstract class Animal(
    val symbol: String,
    val maxCountPerCell: Int,
    val speed: Int,
    val foodNeeded: Double
) {
    var satiety: Double = foodNeeded
    open val foodValue: Double = 0.0  // Добавлено foodValue по умолчанию

    abstract fun eat(cell: Cell)
    abstract fun move(island: Island, currentPosition: Pair<Int, Int>)  // Указаны типы Pair
    abstract fun reproduce(cell: Cell)

    fun die(cell: Cell) {
        cell.removeAnimal(this)
    }
}

// Класс для хищников
abstract class Predator(symbol: String, maxCountPerCell: Int, speed: Int, foodNeeded: Double) : Animal(symbol, maxCountPerCell, speed, foodNeeded)

// Класс для травоядных
abstract class Herbivore(symbol: String, maxCountPerCell: Int, speed: Int, foodNeeded: Double) : Animal(symbol, maxCountPerCell, speed, foodNeeded)

// Конкретные классы животных
class Wolf : Predator("🐺", 30, 3, 8.0) {
    override val foodValue: Double = 8.0  // Добавлено foodValue для волка

    override fun eat(cell: Cell) {
        // Ищем первую попавшуюся жертву, чтобы не итерироваться по всему списку, если нашли
        val rabbit = cell.herbivores.firstOrNull { it is Rabbit } as? Rabbit // Безопасное приведение типа
        if (rabbit != null && Random.nextInt(100) < 60) {
            satiety = min(satiety + rabbit.foodValue, foodNeeded) // Чтобы не "переедал"
            cell.removeAnimal(rabbit)
        }
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed) // Чтобы не становился отрицательным
    }

    override fun reproduce(cell: Cell) {
        if (cell.predators.count { it is Wolf } >= 2 && Random.nextInt(100) < 15 && cell.predators.size < maxCountPerCell) {
            cell.addPredator(Wolf())
        }
    }
}

class Rabbit : Herbivore("🐇", 150, 2, 0.45) {
    override val foodValue: Double = 0.45

    override fun eat(cell: Cell) {
        val plantsToEat = min(cell.plants.toDouble(), foodNeeded - satiety)  // Преобразование в Double для корректности
        satiety = min(satiety + plantsToEat, foodNeeded) // Чтобы не "переедал"
        cell.plants -= plantsToEat.toInt() // Возврат к Int
    }

    override fun move(island: Island, currentPosition: Pair<Int, Int>) {
        repeat(speed) {
            val direction = Direction.random()
            val newPosition = island.getValidPosition(currentPosition, direction)
            island.moveAnimal(this, currentPosition, newPosition)
        }
        satiety = max(0.0, satiety - foodNeeded * 0.1 * speed) // Чтобы не становился отрицательным
    }

    override fun reproduce(cell: Cell) {
        if (cell.herbivores.count { it is Rabbit } >= 2 && Random.nextInt(100) < 15 && cell.herbivores.size < maxCountPerCell) {
            cell.addHerbivore(Rabbit())
        }
    }
}

// Класс для клетки
data class Cell(
    var plants: Int = 0,
    val predators: MutableList<Predator> = mutableListOf(), // Указан тип списка
    val herbivores: MutableList<Herbivore> = mutableListOf() // Указан тип списка
)

{
    fun removeAnimal(animal: Animal) {
        when (animal) {
            is Predator -> predators.remove(animal)
            is Herbivore -> herbivores.remove(animal)
        }
    }

    fun addPredator(predator: Predator) {
        if (predators.size < predator.maxCountPerCell) {
            predators.add(predator)
        }
    }

    fun addHerbivore(herbivore: Herbivore) {
        if (herbivores.size < herbivore.maxCountPerCell) {
            herbivores.add(herbivore)
        }
    }
}

// Класс для острова
class Island(val width: Int, val height: Int) {
    val cells = Array(height) { Array(width) { Cell() } }
    private val scheduledPool = ScheduledThreadPoolExecutor(1) // Оптимизировано количество потоков
    private val taskPool = ForkJoinPool.commonPool() // Использовать commonPool

    fun initialize() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                cells[y][x].plants = Random.nextInt(101)
                if (Random.nextDouble() < 0.1) cells[y][x].addPredator(Wolf()) // Улучшено распределение
                if (Random.nextDouble() < 0.2) cells[y][x].addHerbivore(Rabbit()) // Улучшено распределение
            }
        }
    }

    fun growPlants() {
        processCells { cell ->
            if (cell.plants < 200) {
                cell.plants = min(200, cell.plants + Random.nextInt(1, 10))  // Рост растений случайный, но ограничен 200
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
        if (from == to) return // Животное не двигается
        val fromCell = cells[from.second][from.first]
        val toCell = cells[to.second][to.first]
        synchronized(fromCell) {
            synchronized(toCell) {
                when (animal) {
                    is Predator -> {
                        if (fromCell.predators.remove(animal)) { // Сначала удаляем, проверяем, что удалили
                            if (toCell.predators.size < animal.maxCountPerCell) { // Проверяем лимит перед добавлением
                                toCell.addPredator(animal)
                            } else {
                                fromCell.addPredator(animal) // Если не поместилось, возвращаем обратно
                            }
                        }
                    }
                    is Herbivore -> {
                        if (fromCell.herbivores.remove(animal)) {
                            if (toCell.herbivores.size < animal.maxCountPerCell) {
                                toCell.addHerbivore(animal)
                            } else {
                                fromCell.addHerbivore(animal) // Если не поместилось, возвращаем обратно
                            }
                        }
                    }
                }
            }
        }
    }

    fun startSimulation() {
        scheduledPool.scheduleAtFixedRate({
            taskPool.execute { // Use execute instead of submit for fire-and-forget
                try {
                    growPlants()
                    processCells { cell ->
                        cell.predators.forEach { it.eat(cell) }
                        cell.herbivores.forEach { it.eat(cell) }
                    }
                    processCells { cell ->
                        //Получение координат ячейки
                        val x = cells.indexOfFirst { it.contains(cell) }
                        val y = cells[x].indexOf(cell)
                        cell.predators.forEach { it.move(this, Pair(y,x)) }
                        cell.herbivores.forEach { it.move(this, Pair(y,x)) }
                    }
                    processCells { cell ->
                        cell.predators.removeIf { it.satiety <= 0 } // Удаление умерших хищников
                        cell.herbivores.removeIf { it.satiety <= 0 } // Удаление умерших травоядных
                    }
                    processCells { cell ->
                        cell.predators.forEach { it.reproduce(cell) }
                        cell.herbivores.forEach { it.reproduce(cell) }
                    }
                } catch (e: Exception) {
                    println("Exception during simulation step: ${e.message}")
                    e.printStackTrace()  // Добавлено логирование ошибки
                }
            }
        }, 0, 1, TimeUnit.SECONDS)
    }

    fun stopSimulation() {
        scheduledPool.shutdown()
        taskPool.shutdown()
        scheduledPool.awaitTermination(5, TimeUnit.SECONDS) // Добавлено ожидание завершения
        taskPool.awaitTermination(5, TimeUnit.SECONDS) // Добавлено ожидание завершения
    }

    fun render() {
        print("\u001B[H\u001B[2J")
        cells.forEach { row ->
            row.forEach { cell ->
                val displaySymbol = when {
                    cell.plants > 0 -> "\u001B[32m🌿\u001B[0m"
                    cell.predators.isNotEmpty() -> cell.predators.first().symbol
                    cell.herbivores.isNotEmpty() -> cell.herbivores.first().symbol
                    else -> "\u001B[33m░\u001B[0m"
                }
                print(displaySymbol)
            }
            println()
        }
    }
}

// Направления движения
enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    companion object {
        fun random(): Direction {
            return values()[Random.nextInt(values().size)]
        }
    }
}

// Главная функция
fun main() {
    val island = Island(100, 20)
    island.initialize()
    island.startSimulation()

    while (true) {
        Thread.sleep(1000)
        island.render()
    }
}