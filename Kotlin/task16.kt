package Kotlin

fun main() {
    val matrix1 = Matrix(arrayOf(doubleArrayOf(1.0, 2.0), doubleArrayOf(3.0, 4.0)))
    val matrix2 = Matrix(arrayOf(doubleArrayOf(5.0, 6.0), doubleArrayOf(7.0, 8.0)))

    val sum = matrix1 + matrix2
    val product = matrix1 * matrix2

    println("Matrix 1:\n$matrix1")
    println("Matrix 2:\n$matrix2")
    println("Sum:\n$sum")
    println("Product:\n$product")
}

class Matrix(private val data: Array<DoubleArray>) {
    operator fun plus(other: Matrix): Matrix {
        val result = Array(data.size) { DoubleArray(data[0].size) }
        for (i in data.indices) {
            for (j in data[i].indices) {
                result[i][j] = this.data[i][j] + other.data[i][j]
            }
        }
        return Matrix(result)
    }

    operator fun times(other: Matrix): Matrix {
        val result = Array(data.size) { DoubleArray(other.data[0].size) }
        for (i in data.indices) {
            for (j in other.data[0].indices) {
                for (k in other.data.indices) {
                    result[i][j] += this.data[i][k] * other.data[k][j]
                }
            }
        }
        return Matrix(result)
    }

    override fun toString(): String {
        return data.joinToString("\n") { it.joinToString(" ") }
    }
}