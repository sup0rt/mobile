package Kotlin.basicsyntaxis

fun main() {
    val counter1 = Counter()
    val counter2 = Counter()
    println("Counter: ${Counter.getCount()}")
    Counter.increment()
    println("Counter after increment: ${Counter.getCount()}")
}

class Counter {
    companion object {
        private var count = 0

        fun increment() {
            count++
        }

        fun getCount(): Int {
            return count
        }
    }

    init {
        increment()
    }
}