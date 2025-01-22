package Kotlin

fun main() {
    val num1 = ComplexNumber(2.0, 3.0)
    val num2 = ComplexNumber(1.0, -1.0)

    val sum = num1 + num2
    val difference = num1 - num2

    println("Sum: $sum")
    println("Difference: $difference")
}

data class ComplexNumber(val real: Double, val imaginary: Double) {
    operator fun plus(other: ComplexNumber) = ComplexNumber(real + other.real, imaginary + other.imaginary)
    operator fun minus(other: ComplexNumber) = ComplexNumber(real - other.real, imaginary - other.imaginary)
}