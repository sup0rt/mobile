package Kotlin.basicsyntaxis

fun main() {
    val account = BankAccount("12345")
    account.deposit(100.0)
    println("Balance: ${account.getBalance()}")
    account.withdraw(50.0)
    println("Balance after withdrawal: ${account.getBalance()}")
    account.withdraw(100.0)
    println("Balance: ${account.getBalance()}")
}

class BankAccount(private val accountNumber: String) {
    private var balance: Double = 0.0

    fun deposit(amount: Double) {
        balance += amount
    }

    fun withdraw(amount: Double) {
        if (amount <= balance) {
            balance -= amount
        } else {
            println("Insufficient funds")
        }
    }

    fun getBalance(): Double {
        return balance
    }
}