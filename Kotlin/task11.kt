package Kotlin

fun main() {
    val store = Store()
    val product1 = Product("Laptop", 1200.0, 5)
    val product2 = Product("Mouse", 25.0, 20)

    store.addProduct(product1)
    store.addProduct(product2)

    val foundProduct = store.findProductByName("Laptop")
    println(foundProduct)
    store.removeProduct("Mouse")

    val foundProduct2 = store.findProductByName("Mouse")
    println(foundProduct2)
}

data class Product(val name: String, val price: Double, var quantity: Int)

class Store {
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun removeProduct(productName: String) {
        products.removeIf { it.name == productName }
    }

    fun findProductByName(productName: String): Product? {
        return products.find { it.name == productName }
    }
}