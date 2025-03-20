package Kotlin.threadsDecoratorIoNiO

import java.nio.ByteBuffer
import java.nio.channels.AsynchronousFileChannel
import java.nio.channels.CompletionHandler
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.system.exitProcess

fun main() {
    val inputFile = "kotlin/threadsDecoratorIoNiO/files/input.txt"
    readAsyncFile(inputFile)
}

fun readAsyncFile(inputPath: String) {
    val path: Path = Paths.get(inputPath)
    val executor: ExecutorService = Executors.newFixedThreadPool(1)
    var channel: AsynchronousFileChannel? = null
    try {
        channel = AsynchronousFileChannel.open(path, setOf(StandardOpenOption.READ), executor)
        val buffer = ByteBuffer.allocate(2048)
        val attachment = ReadAttachment(buffer, channel)
        channel.read(buffer, 0, attachment, ReadCompletionHandler())
        while (!attachment.isCompleted) {
            Thread.sleep(100)
        }

    } catch (e: Exception) {
        println("error reading file: ${e.message}")
        exitProcess(1)
    } finally {
        channel?.close()
        executor.shutdown()
    }
}

class ReadAttachment(
    val buffer: ByteBuffer,
    val channel: AsynchronousFileChannel,
    var isCompleted: Boolean = false
)

class ReadCompletionHandler : CompletionHandler<Int, ReadAttachment> {
    override fun completed(result: Int, attachment: ReadAttachment) {
        if (result == -1) {
            attachment.isCompleted = true
            return
        }
        attachment.buffer.flip()
        val bytes = ByteArray(attachment.buffer.remaining())
        attachment.buffer.get(bytes)
        println(String(bytes))
        attachment.buffer.clear()
        attachment.channel.read(attachment.buffer, 0, attachment, this)

    }

    override fun failed(exc: Throwable, attachment: ReadAttachment) {
        println("error reading file: ${exc.message}")
        attachment.isCompleted = true
    }
}