package Kotlin.threadsDecoratorIoNiO

fun main() {
    val text = "    REWY FGFFB d thvdh tjrj  "
    println("$text")

    var processor: TextProcessor = SimpleTextProcessor()
    println("${processor.process(text)}")

    processor = UpperCaseDecorator(SimpleTextProcessor())
    println("${processor.process(text)}")

    processor = TrimDecorator(SimpleTextProcessor())
    println("${processor.process(text)}")

    processor = ReplaceDecorator(SimpleTextProcessor())
    println("${processor.process(text)}")

    processor = UpperCaseDecorator(TrimDecorator(ReplaceDecorator(SimpleTextProcessor())))
    println("${processor.process(text)}")

    processor = TrimDecorator(UpperCaseDecorator(ReplaceDecorator(SimpleTextProcessor())))
    println("${processor.process(text)}")

    processor = TrimDecorator(UpperCaseDecorator(SimpleTextProcessor()))
    println("${processor.process(text)}")
}

interface TextProcessor {
    fun process(text: String): String
}

class SimpleTextProcessor : TextProcessor {
    override fun process(text: String): String {
        return text
    }
}

class UpperCaseDecorator(private val textProcessor: TextProcessor) : TextProcessor {
    override fun process(text: String): String {
        return textProcessor.process(text).uppercase()
    }
}

class TrimDecorator(private val textProcessor: TextProcessor) : TextProcessor {
    override fun process(text: String): String {
        return textProcessor.process(text).trim()
    }
}

class ReplaceDecorator(private val textProcessor: TextProcessor) : TextProcessor {
    override fun process(text: String): String {
        return textProcessor.process(text).replace(" ", "_")
    }
}