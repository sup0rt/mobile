package Kotlin.basicsyntaxis

fun main() {
        val person1 = Person("Alice", 30, "Female")
        person1.displayInfo()
        person1.increaseAge()
        person1.displayInfo()
        person1.changeName("Alice Smith")
        person1.displayInfo()
}

open class Person(var name: String, var age: Int, var gender: String) {
    fun displayInfo() {
        println("Name: $name, Age: $age, Gender: $gender")
    }

    fun increaseAge() {
        age++
    }

    fun changeName(newName: String) {
        name = newName
    }
}