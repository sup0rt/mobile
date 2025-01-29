package Kotlin.basicsyntaxis

fun main() {
    println("ID 1: ${UniqueID.generateID()}")
    println("ID 2: ${UniqueID.generateID()}")
    println("ID 3: ${UniqueID.generateID()}")
}

class UniqueID {
    companion object {
        private var idCounter = 0

        fun generateID(): Int {
            return idCounter++
        }
    }
}