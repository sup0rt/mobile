package Kotlin.basicsyntaxis

fun main(){
    val worker1 = Worker("Bob", 25, "Male", 50000.0)
    worker1.displayInfo()
    val worker2 = Worker("Charlie", 28, "Male", 60000.0)
    val manager1 = Manager("Eve", 35, "Female", 100000.0, listOf(worker1, worker2))
    manager1.displayInfo()
    println("Manager has ${manager1.subordinates.size} subordinates")
}

open class Worker(name: String, age: Int, gender: String, var salary: Double) : Person(name, age, gender)

class Manager(name: String, age: Int, gender: String, salary: Double, var subordinates: List<Worker>) : Worker(name, age, gender, salary)