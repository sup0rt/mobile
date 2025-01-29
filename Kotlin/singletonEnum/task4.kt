package Kotlin.singletonEnum

fun main(){
    rusSeason(Season.SUMMER)
    rusSeason(Season.AUTUMN)
    rusSeason(Season.WINTER)
    rusSeason(Season.SPRING)
}

enum class Season {
    WINTER, SPRING, SUMMER, AUTUMN
}

fun rusSeason(name: Season){
    when {
        name==Season.WINTER -> println("Зима")
        name==Season.AUTUMN -> println("Осень")
        name==Season.SPRING -> println("Весна")
        name==Season.SUMMER -> println("Лето")
    }
}
