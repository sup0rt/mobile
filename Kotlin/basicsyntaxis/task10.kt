package Kotlin.basicsyntaxis

fun mainUniversity() {
    val university = University()
    val student1 = Student("Bob", "A", 3.5)
    val student2 = Student("Alice", "B", 4.2)
    val student3 = Student("Charlie", "A", 3.8)

    university.addStudent(student1)
    university.addStudent(student2)
    university.addStudent(student3)

    println("Unsorted students:")
    university.filterByGrade(0.0).forEach { println("    ${it.name}: ${it.grade}") }

    university.sortByName()
    println("Sorted students by name:")
    university.filterByGrade(0.0).forEach { println("    ${it.name}: ${it.grade}") }

    println("Students with a grade of 3.8 or higher:")
    university.filterByGrade(3.8).forEach { println("    ${it.name}: ${it.grade}") }
}

data class Student(val name: String, val group: String, val grade: Double)

class University {
    private val students = mutableListOf<Student>()

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun sortByName() {
        students.sortBy { it.name }
    }

    fun filterByGrade(minGrade: Double): List<Student> {
        return students.filter { it.grade >= minGrade }
    }
}