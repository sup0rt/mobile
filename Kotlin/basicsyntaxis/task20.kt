package Kotlin.basicsyntaxis

fun main() {
    val game = Game()
    game.displayBoard()
    game.play(0, 0)
    game.displayBoard()
    game.play(1, 1)
    game.displayBoard()
    game.play(0, 1)
    game.displayBoard()
    game.play(2, 2)
    game.displayBoard()
    game.play(0, 2)
    game.displayBoard()
}

class Game {
    private val board = Array(3) { Array(3) { ' ' } }
    private var currentPlayer = 'X'

    fun play(x: Int, y: Int) {
        if (board[x][y] == ' ') {
            board[x][y] = currentPlayer
            if (checkWin()) {
                println("Player $currentPlayer wins!")
            } else {
                currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
            }
        } else {
            println("Cell already occupied!")
        }
    }

    private fun checkWin(): Boolean {
        // Проверка по строкам, столбцам и диагоналям
        for (i in 0..2) {
            if (board[i].all { it == currentPlayer } || board.all { it[i] == currentPlayer }) return true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true
        return false
    }

    fun displayBoard() {
        for (row in board) {
            println(row.joinToString(" | "))
        }
    }
}