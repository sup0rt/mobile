package Kotlin.threadsDecoratorIoNiO

import java.io.FileNotFoundException
import java.io.IOException
import java.nio.channels.FileChannel
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import kotlin.system.measureNanoTime


fun main() {
    val inputFile = "Kotlin/threadsDecoratorIoNiO/files/input.txt"
    val outputFileNIO = "Kotlin/threadsDecoratorIoNiO/files/outNIO2.txt"

    println("\nNIO copying file test:")
    copyFileNIO(inputFile, outputFileNIO)

}

fun copyFileNIO(inputPath: String, outputPath: String) {
    val input = Paths.get(inputPath)
    val output = Paths.get(outputPath)

    try {
        measureNanoTime{
            FileChannel.open(input, StandardOpenOption.READ).use { inputChannel ->
                FileChannel.open(output, StandardOpenOption.WRITE, StandardOpenOption.CREATE).use { outputChannel ->
                    inputChannel.transferTo(0, inputChannel.size(), outputChannel)
                }
            }
        }
        println("NIO Copying file successful")

    }
    catch (e: FileNotFoundException){
        println("file not fond")
    }
    catch (e: IOException) {
        println("IO error: ${e.message}")
    }
}