package Kotlin.basicsyntaxis

fun main() {
    val topLeft = Point(1.0, 5.0)
    val bottomRight = Point(6.0, 1.0)
    val rectangle = Rectangle2(topLeft, bottomRight)

    println("Rectangle Area: ${rectangle.getArea()}")
}

data class Point(val x: Double, val y: Double)

class Rectangle2(private val topLeft: Point, private val bottomRight: Point) {
    fun getArea(): Double {
        val width = bottomRight.x - topLeft.x
        val height = topLeft.y - bottomRight.y
        return width * height
    }
}