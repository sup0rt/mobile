package Kotlin.island.Model

import kotlin.random.Random

enum class Direction {
    UP, DOWN, LEFT, RIGHT;

    companion object {
        fun random(): Direction {
            return entries[Random.nextInt(entries.size)]
        }
    }
}