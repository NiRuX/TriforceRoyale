package zelda.items

import zelda.engine.GObject
import zelda.engine.Game
import zelda.link.Link

/**
 *
 * @author Tom
 */
class Rupee(game: Game?, x: Int, y: Int) : GObject(game!!, x, y, 8, 14, "images/rupee.png") {
    init {
        spriteLoc.put("rupee1", Rectangle(0, 0, 8, 14))
        spriteLoc.put("rupee2", Rectangle(25, 0, 8, 14))
        spriteLoc.put("rupee3", Rectangle(50, 0, 8, 14))
        sprite.setSprite(spriteLoc.get("rupee1"))
        animationInterval = 100
        animation = rupeeAnimation
        z = 1
        liquid = true
    }

    public override fun collision(obj: GObject?) {
        if (obj is Link) {
            alive = false
        }
    }

    companion object {
        private val rupeeAnimation = arrayOf(
            "rupee1",
            "rupee2",
            "rupee3",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1",
            "rupee1"
        )
    }
}