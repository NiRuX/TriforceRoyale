package zelda.items

import zelda.engine.GObject
import zelda.engine.Game
import zelda.link.Link

/**
 *
 * @author Tom
 */
class Heart(game: Game?, x: Int, y: Int) : GObject(game!!, x, y, 11, 10, "images/heart1.png") {
    init {
        spriteLoc.put("heart", Rectangle(0, 0, 11, 10))
        sprite.setSprite(spriteLoc.get("heart"))
        animation = heartAnimation
        liquid = true
    }

    public override fun collision(obj: GObject?) {
        //System.out.println("Collision");
        if (obj is Link) {
            alive = false
        }
    }

    companion object {
        private val heartAnimation = arrayOf("heart")
    }
}