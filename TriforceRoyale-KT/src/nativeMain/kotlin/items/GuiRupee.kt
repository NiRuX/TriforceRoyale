package zelda.items

import zelda.engine.GObject
import zelda.engine.Game

/**
 *
 * @author Bas Harteveld
 */
class GuiRupee(game: Game?, x: Int, y: Int) : GObject(game!!, x, y, 11, 10, "images/rupeegui2.png") {
    init {
        spriteLoc.put("rupee", Rectangle(0, 0, 11, 10))
        animation = rupeeAnimation
        z = 2
        screenAdjust = false
        checkcollision = false
        liquid = true
    }

    companion object {
        private val rupeeAnimation = arrayOf("rupee")
    }
}