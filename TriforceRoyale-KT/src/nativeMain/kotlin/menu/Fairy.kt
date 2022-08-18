package zelda.menu

import zelda.engine.GObject
import zelda.engine.Game

/**
 *
 * @author maartenhus
 */
class Fairy(game: Game?, x: Int, y: Int) : GObject(game!!, x, y, 14, 16, "images/fairy.png") {
    init {
        spriteLoc.put("Fly1", Rectangle(0, 0, 14, 16))
        spriteLoc.put("Fly2", Rectangle(20, 0, 14, 16))
        sprite.setSprite(spriteLoc.get("Fly1"))
        animationInterval = 250
        animation = flyani
    }

    companion object {
        private val flyani = arrayOf("Fly1", "Fly2")
    }
}