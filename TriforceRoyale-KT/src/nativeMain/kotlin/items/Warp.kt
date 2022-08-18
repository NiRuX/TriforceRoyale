package zelda.items

import zelda.engine.GObject
import zelda.engine.Game

/**
 *
 * @author maartenhus
 */
class Warp(game: Game?, x: Int, y: Int) : GObject(game!!, x, y, 1, 1, "images/items.png") {
    init {
        spriteLoc.put("Warp1", Rectangle(40, 0, 16, 16))
        spriteLoc.put("Warp2", Rectangle(60, 0, 16, 16))
        spriteLoc.put("Warp3", Rectangle(80, 0, 16, 16))
        spriteLoc.put("Warp4", Rectangle(0, 58, 16, 16))
        sprite.setSprite(spriteLoc.get("Warp1"))
        animation = warpani
        liquid = true
    }

    fun setActive() {
        animation = if (animation == warpani) {
            emptyani
        } else {
            warpani
        }
    }

    companion object {
        private val warpani = arrayOf("Warp1", "Warp2", "Warp3")
        private val emptyani = arrayOf("Warp4")
    }
}