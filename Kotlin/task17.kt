package Kotlin

fun main() {
    val player = Player("Hero", 100)
    val enemy = Enemy("Goblin", 50)
    val sword = Weapon("Sword")

    player.attack(enemy, sword)
}

class Player(val name: String, var health: Int) {
    fun attack(enemy: Enemy, weapon: Weapon) {
        println("$name attacks ${enemy.name} with ${weapon.type}")
        enemy.takeDamage(10) // фиксированный урон
    }
}

class Enemy(val name: String, var health: Int) {
    fun takeDamage(amount: Int) {
        health -= amount
        println("$name takes $amount damage, remaining health: $health")
    }
}

class Weapon(val type: String)