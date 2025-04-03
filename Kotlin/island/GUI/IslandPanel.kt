package Kotlin.island.GUI

import Kotlin.island.Model.Island
import java.awt.Color
import java.awt.Graphics
import javax.swing.JPanel

class IslandPanel(private val island: Island) : JPanel() {
    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        val cellWidth = width / island.width
        val cellHeight = height / island.height

        for (y in 0 until island.height) {
            for (x in 0 until island.width) {
                val cell = island.cells[y][x]
                val color = when {
                    cell.predators.isNotEmpty() -> Color.RED
                    cell.herbivores.isNotEmpty() -> Color.GRAY
                    cell.plants > 0 -> Color(34, 139, 34)
                    else -> Color.LIGHT_GRAY
                }
                g.color = color
                g.fillRect(x * cellWidth, y * cellHeight, cellWidth, cellHeight)
            }
        }
    }
}