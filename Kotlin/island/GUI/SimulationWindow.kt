package Kotlin.island.GUI

import Kotlin.island.Model.Island
import javax.swing.JFrame

class SimulationWindow(private val island: Island) : JFrame("Island simulation") {
    private val islandPanel = IslandPanel(island)

    init{
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        add(islandPanel)
        setSize(800,600)
        isVisible = true
    }

    fun startSimulation(){
        island.startSimulation {
            islandPanel.repaint()
        }
    }
}