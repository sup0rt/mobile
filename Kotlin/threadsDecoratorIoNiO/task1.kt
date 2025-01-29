package Kotlin.threadsDecoratorIoNiO

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileNotFoundException
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

fun main(){
    val inputFile = "Kotlin/threadsDecoratorIoNiO/files/input.txt"
    val outputFile = "Kotlin/threadsDecoratorIoNiO/files/out.txt"

    copyFile(inputFile, outputFile)
}

fun copyFile(inName: String, outName: String){
    try{
        BufferedReader(FileReader(inName)).use {
            reader ->BufferedWriter(FileWriter(outName)).use {
                writer -> var line: String?
                while (reader.readLine().also { line = it } != null){
                    val upperCase = line?.uppercase() ?: ""
                    writer.write(upperCase)
                    writer.newLine()
                }
            }
        }
        println("copying success")
    }
    catch (e: FileNotFoundException){
        println("file not fond")
    }
    catch (e: IOException){
        println("IO erorr: ${e.message}")
    }
}