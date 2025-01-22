package Kotlin

fun main() {
    val customer = Customer("Alice")
    val order1 = Order(listOf(Product2("Book", 12.99), Product2("Pen", 1.99)))
    val order2 = Order(listOf(Product2("Notebook", 5.99)))

    val orderSystem = OrderSystem()
    orderSystem.addOrder(order1)
    orderSystem.addOrder(order2)

    orderSystem.showOrderHistory()
}

data class Product2(val name: String, val price: Double)

class Order(val products: List<Product2>) {
    fun totalCost(): Double {
        return products.sumOf { it.price }
    }
}

class Customer(val name: String)

class OrderSystem {
    private val orders = mutableListOf<Order>()

    fun addOrder(order: Order) {
        orders.add(order)
    }

    fun showOrderHistory() {
        for (order in orders) {
            println("Order total: ${order.totalCost()}")
        }
    }
}