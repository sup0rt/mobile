package Kotlin.island

import Kotlin.island.Model.Island
import Kotlin.island.GUI.SimulationWindow

fun main(){
    val island = Island(10, 10)
    island.initialize()
    SimulationWindow(island).startSimulation()
}