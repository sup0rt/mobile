package Kotlin.multythreads

import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class Counter {
    private var count = 0
    private val lock = ReentrantLock()

    fun increment() {
        lock.lock()
        try {
            count++
        }
        finally {
            lock.unlock()
        }
    }

    fun getCount(): Int {
        return count
    }
}

fun main() {
    val counter = Counter()
    val threads = mutableListOf<Thread>()

    for (i in 1..5) {
        val thread = thread {
            repeat(1000) {
                counter.increment()
            }
        }
        threads.add(thread)
    }

    threads.forEach { it.join() }

    println("Final count: ${counter.getCount()}")
}