package Kotlin.singletonEnum

fun main() {
    val order = Order("Salt and pepper")

    order.printOrder()

    order.changeStatus(OrderStatus.IN_PROGRESS)
    order.printOrder()

    order.changeStatus(OrderStatus.DELIVERED)
    order.printOrder()

    order.changeStatus(OrderStatus.CANCELLED)
    order.printOrder()
}

enum class OrderStatus {
    NEW, IN_PROGRESS, DELIVERED, CANCELLED
}

class Order(val description: String, var status: OrderStatus = OrderStatus.NEW) {
    fun changeStatus(newStatus: OrderStatus) {
        status = newStatus
    }

    fun printOrder() {
        println("Order Description: $description, Status: $status")
    }
}