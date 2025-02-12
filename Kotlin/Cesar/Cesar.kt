import java.awt.*

import java.io.File
import javax.swing.*



fun main() {

}
private val textInputArea = JTextArea(10, 30)
private val textOutputArea = JTextArea(10, 30)
private val keyTextField = JTextField(5)
private val encryptButton = JButton("Зашифровать")
private val decryptButton = JButton("Расшифровать")
private val bruteForceButton = JButton("Brute Force")
private val languageComboBox = JComboBox(arrayOf("Русский", "Английский"))
private val inputFileButton = JButton("Выбрать входной файл")
private val outputFileButton = JButton("Выбрать выходной файл")
private val inputFilePath = JTextField(20)
private val outputFilePath = JTextField(20)

private var selectedInputFile: File? = null
private var selectedOutputFile: File? = null
private var currentLanguage = "Английский"
private val russianAlphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя"
private val englishAlphabet = "abcdefghijklmnopqrstuvwxyz"


private fun encryptText(text: String) {
    val key = getKey() ?: return
    val alphabet = getAlphabet()
    val encrypted = cesarEncryption(text, key, alphabet)
    textOutputArea.text = encrypted
}

private fun decryptText(text: String) {
    val key = getKey() ?: return
    val alphabet = getAlphabet()
    val decrypted = cesarEncryption(text, -key, alphabet)
    textOutputArea.text = decrypted
}

private fun bruteForceText(text: String) {
    val alphabet = getAlphabet()
    var bestMatchText = ""
    var bestMatchKey = -1
    var bestMatchScore = 0

    for (key in 0 until alphabet.length) {
        val decryptedText = cesarEncryption(text, -key, alphabet)
        val score = countCommonWords(decryptedText.lowercase(), alphabet)

        if (score > bestMatchScore) {
            bestMatchScore = score
            bestMatchText = decryptedText
            bestMatchKey = key
        }
    }

    textOutputArea.text = "Наиболее вероятный ключ: $bestMatchKey\n" + bestMatchText
}

private fun getKey(): Int? {
    return try {
        keyTextField.text.toInt()
    } catch (e: NumberFormatException) {
        JOptionPane.showMessageDialog(this, "Ключ должен быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE)
        null
    }
}

private fun getAlphabet(): String {
    return when (currentLanguage) {
        "Русский" -> russianAlphabet
        "Английский" -> englishAlphabet
        else -> englishAlphabet
    }
}

private fun cesarEncryption(input: String, key: Int, alphabet: String): String {
    val alphabetSize = alphabet.length
    val normalizedKey = key % alphabetSize

    return input.map { char ->
        if (char.isLetter()) {
            val isUpperCase = char.isUpperCase()
            val charLower = char.lowercaseChar()
            val charIndex = alphabet.indexOf(charLower)

            if (charIndex != -1) {
                val shiftedIndex = (charIndex + normalizedKey + alphabetSize) % alphabetSize
                val shiftedChar = alphabet[shiftedIndex]

                if (isUpperCase) {
                    shiftedChar.uppercaseChar()
                } else {
                    shiftedChar
                }
            } else {
                char
            }
        } else {
            char
        }
    }.joinToString("")
}


private fun countCommonWords(text: String, alphabet: String): Int {

    return text.count { alphabet.contains(it) }
}