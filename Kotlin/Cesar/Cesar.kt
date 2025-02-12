import java.awt.*
import java.awt.event.ActionEvent
import java.io.File
import javax.swing.*

class CaesarCipherApp : JFrame("Шифр Цезаря") {

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

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = FlowLayout()


        val keyLabel = JLabel("Ключ:")
        val languageLabel = JLabel("Язык:")
        val inputFileLabel = JLabel("Входной файл:")
        val outputFileLabel = JLabel("Выходной файл:")


        add(JLabel("Текст:"))
        add(JScrollPane(textInputArea))
        add(JLabel("Результат:"))
        add(JScrollPane(textOutputArea))
        add(keyLabel)
        add(keyTextField)
        add(languageLabel)
        add(languageComboBox)
        add(inputFileLabel)
        add(inputFilePath)
        add(inputFileButton)
        add(outputFileLabel)
        add(outputFilePath)
        add(outputFileButton)
        add(encryptButton)
        add(decryptButton)
        add(bruteForceButton)

        languageComboBox.addActionListener {
            currentLanguage = languageComboBox.selectedItem as String
            println("Selected language: $currentLanguage")
        }

        inputFileButton.addActionListener {
            val fileChooser = JFileChooser()
            val result = fileChooser.showOpenDialog(this)
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedInputFile = fileChooser.selectedFile
                inputFilePath.text = selectedInputFile?.absolutePath
                println("Input file selected: ${selectedInputFile?.absolutePath}")
            }
        }

        outputFileButton.addActionListener {
            val fileChooser = JFileChooser()
            val result = fileChooser.showSaveDialog(this)
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedOutputFile = fileChooser.selectedFile
                outputFilePath.text = selectedOutputFile?.absolutePath
                println("Output file selected: ${selectedOutputFile?.absolutePath}")
            }
        }

        encryptButton.addActionListener {
            performAction {
                encryptText(textInputArea.text)
            }
        }

        decryptButton.addActionListener {
            performAction {
                decryptText(textInputArea.text)
            }
        }

        bruteForceButton.addActionListener {
            performAction {
                bruteForceText(textInputArea.text)
            }
        }

        setSize(500, 600)
        isVisible = true
    }

    private fun performAction(action: () -> Unit) {
        try {
            action()
        } catch (e: Exception) {
            e.printStackTrace()
            JOptionPane.showMessageDialog(this, "Ошибка: ${e.message}", "Ошибка", JOptionPane.ERROR_MESSAGE)
        }
    }

    private fun encryptText(text: String) {
        println("Encrypting text...")
        val key = getKey() ?: return
        val alphabet = getAlphabet()

        if (selectedInputFile != null && selectedOutputFile != null) {
            try {
                println("Reading from file: ${selectedInputFile?.absolutePath}")
                val inputTextFromFile = selectedInputFile?.readText() ?: ""
                println("Read text from file: ${inputTextFromFile.take(50)}...")

                val encrypted = cesarEncryption(inputTextFromFile, key, alphabet)
                println("Text encrypted, writing to file: ${selectedOutputFile?.absolutePath}")
                selectedOutputFile?.writeText(encrypted)
                textOutputArea.text = "Файл зашифрован!"
                println("Encryption complete.")

            } catch (e: Exception) {
                e.printStackTrace()
                JOptionPane.showMessageDialog(this, "Ошибка при шифровании файла: ${e.message}", "Ошибка", JOptionPane.ERROR_MESSAGE)
            }
        } else {
            println("Encrypting text from input area...")
            val encrypted = cesarEncryption(text, key, alphabet)
            textOutputArea.text = encrypted
            println("Encryption complete.")
        }
    }


    private fun decryptText(text: String) {
        println("Decrypting text...")
        val key = getKey() ?: return
        val alphabet = getAlphabet()

        if (selectedInputFile != null && selectedOutputFile != null) {
            try {
                println("Reading from file: ${selectedInputFile?.absolutePath}")
                val inputTextFromFile = selectedInputFile?.readText() ?: ""
                println("Read text from file: ${inputTextFromFile.take(50)}...")
                val decrypted = cesarEncryption(inputTextFromFile, -key, alphabet)
                println("Text decrypted, writing to file: ${selectedOutputFile?.absolutePath}")
                selectedOutputFile?.writeText(decrypted)
                textOutputArea.text = "Файл расшифрован!"
                println("Decryption complete.")

            } catch (e: Exception) {
                e.printStackTrace()
                JOptionPane.showMessageDialog(this, "Ошибка при расшифровке файла: ${e.message}", "Ошибка", JOptionPane.ERROR_MESSAGE)
            }

        } else {
            println("Decrypting text from input area...")
            val decrypted = cesarEncryption(text, -key, alphabet)
            textOutputArea.text = decrypted
            println("Decryption complete.")
        }
    }

    private fun bruteForceText(text: String) {
        println("Starting brute force...")
        val alphabet = getAlphabet()
        if (selectedInputFile != null && selectedOutputFile != null) {
            try {
                println("Reading from file: ${selectedInputFile?.absolutePath}")
                val encryptedText = selectedInputFile?.readText() ?: ""
                println("Read text from file for brute force: ${encryptedText.take(50)}...")

                var bestMatchText = ""
                var bestMatchKey = -1
                var bestMatchScore = 0

                for (key in 0 until alphabet.length) {
                    val decryptedText = cesarEncryption(encryptedText, -key, alphabet)
                    val score = countCommonWords(decryptedText.lowercase(), alphabet)

                    println("Brute force - key: $key, score: $score, text: ${decryptedText.take(30)}...")

                    if (score > bestMatchScore) {
                        bestMatchScore = score
                        bestMatchText = decryptedText
                        bestMatchKey = key
                    }
                }
                println("Writing brute force result to file: ${selectedOutputFile?.absolutePath}")
                selectedOutputFile?.writeText("Наиболее вероятный ключ: $bestMatchKey\n" + bestMatchText)
                textOutputArea.text = "Brute force завершен. Наиболее вероятный ключ: $bestMatchKey"
                println("Brute force complete.")
            } catch (e: Exception) {
                e.printStackTrace()
                JOptionPane.showMessageDialog(this, "Ошибка при brute force: ${e.message}", "Ошибка", JOptionPane.ERROR_MESSAGE)
            }
        } else {
            JOptionPane.showMessageDialog(this, "Пожалуйста, выберите входной и выходной файл для brute force", "Ошибка", JOptionPane.ERROR_MESSAGE)
        }
    }


    private fun getKey(): Int? {
        println("Getting key from input...")
        return try {
            val key = keyTextField.text.toInt()
            println("Key: $key")
            key
        } catch (e: NumberFormatException) {
            println("Invalid key format")
            JOptionPane.showMessageDialog(this, "Ключ должен быть числом", "Ошибка", JOptionPane.ERROR_MESSAGE)
            null
        }
    }

    private fun getAlphabet(): String {
        println("Getting alphabet for language: $currentLanguage")
        return when (currentLanguage) {
            "Русский" -> {
                println("Using Russian alphabet")
                russianAlphabet
            }
            "Английский" -> {
                println("Using English alphabet")
                englishAlphabet
            }
            else -> {
                println("Using English alphabet (default)")
                englishAlphabet
            }
        }
    }

    private fun cesarEncryption(input: String, key: Int, alphabet: String): String {
        println("Encrypting/decrypting text with key: $key, alphabet: $alphabet")
        val alphabetSize = alphabet.length
        val normalizedKey = (key % alphabetSize + alphabetSize) % alphabetSize

        val result = input.map { char ->
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
        println("Encrypted/Decrypted text: ${result.take(50)}...")
        return result
    }

    private fun countCommonWords(text: String, alphabet: String): Int {
        println("Counting common words in text: ${text.take(50)}...")

        val count = text.count { alphabet.contains(it) }
        println("Common word count: $count")
        return count
    }
}

fun main() {
    SwingUtilities.invokeLater {
        CaesarCipherApp()
    }
}