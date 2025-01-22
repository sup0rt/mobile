package Kotlin

fun main() {
    val creditCard = CreditCard()
    val payPal = PayPal()

    creditCard.pay(100.0)
    creditCard.refund(50.0)

    payPal.pay(200.0)
    payPal.refund(150.0)
}

interface PaymentSystem {
    fun pay(amount: Double)
    fun refund(amount: Double)
}

class CreditCard : PaymentSystem {
    override fun pay(amount: Double) {
        println("Paid $amount using Credit Card")
    }

    override fun refund(amount: Double) {
        println("Refunded $amount to Credit Card")
    }
}

class PayPal : PaymentSystem {
    override fun pay(amount: Double) {
        println("Paid $amount using PayPal")
    }

    override fun refund(amount: Double) {
        println("Refunded $amount to PayPal")
    }
}