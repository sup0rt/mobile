package Kotlin.threadsDecoratorIoNiO

import java.io.*
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import kotlin.system.measureNanoTime

fun main() {

    val inputFile = "kotlin/threadsDecoratorIoNiO/files/input.txt"
    val outputFileIO = "kotlin/threadsDecoratorIoNiO/files/outIO.txt"
    val outputFileNIO = "kotlin/threadsDecoratorIoNiO/files/outNIO.txt"

    println("\nIO test:")
    measureIOTest(inputFile, outputFileIO)

    println("\nNIO test:")
    measureNIOTest(inputFile, outputFileNIO)

}

fun measureIOTest(inputPath: String, outputPath: String){
    try {
        val readTime = measureNanoTime{
            BufferedReader(FileReader(inputPath)).use { reader ->
                BufferedWriter(FileWriter(outputPath)).use { writer ->
                    var line: String?
                    while (reader.readLine().also { line = it } != null){
                        writer.write(line)
                        writer.newLine()
                    }
                }
            }
        }
        println("IO time: ${readTime/1000000.0} ms")
    }
    catch (e: FileNotFoundException){
        println("file not fond")
    }
    catch (e: IOException){
        println("IO erorr: ${e.message}")
    }
}

fun measureNIOTest(inputPath: String, outputPath: String){
    val input = Paths.get(inputPath)
    val output = Paths.get(outputPath)

    try {
        val readTime = measureNanoTime{
            FileChannel.open(input, StandardOpenOption.READ).use { inputChannel ->
                FileChannel.open(output, StandardOpenOption.WRITE, StandardOpenOption.CREATE).use { outputChannel ->
                    val buffer = ByteBuffer.allocate(8192)
                    while (inputChannel.read(buffer) != -1){
                        buffer.flip()
                        outputChannel.write(buffer)
                        buffer.clear()
                    }
                }
            }
        }
        println("NIO time: ${readTime/1000000.0} ms")

    }
    catch (e: FileNotFoundException){
        println("file not fond")
    }
    catch (e: IOException){
        println("IO erorr: ${e.message}")
    }
}