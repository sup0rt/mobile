package Kotlin

fun main() {
    val circle = Circle(5.0)
    val rectangle = Rectangle(4.0, 6.0)

    println("Circle Area: ${circle.getArea()}")
    println("Rectangle Area: ${rectangle.getArea()}")
}

abstract class Shape {
    abstract fun getArea(): Double
}

class Circle(private val radius: Double) : Shape() {
    override fun getArea(): Double {
        return Math.PI * radius * radius
    }
}

class Rectangle(private val width: Double, private val height: Double) : Shape() {
    override fun getArea(): Double {
        return width * height
    }
}