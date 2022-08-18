package zelda.items

import zelda.engine.GObject
import zelda.engine.Game

/**
 *
 * @author Bas Harteveld
 */
class GuiHeart(game: Game?, x: Int, y: Int) : GObject(game!!, x, y, 11, 10, "images/guihearts2.png") {
    private var full = true

    init {
        spriteLoc.put("full", Rectangle(0, 0, 11, 10))
        spriteLoc.put("empty", Rectangle(11, 0, 11, 10))
        sprite.setSprite(spriteLoc.get("full"))
        animation = fullAnimation
        z = 2
        screenAdjust = false
        checkcollision = false
        liquid = true
        if (i < 5) {
            hearts[i] = this
            i++
        }
    }

    public override fun preAnimation() {
        val empty: Int = 5 - game.getLink().getHealth()

        //System.out.println(empty);
        for (j in 0..4) {
            if (j >= 5 - empty) {
                hearts[j]!!.setFull(false)
            } else {
                hearts[j]!!.setFull(true)
            }
        }
        animation = if (full) {
            fullAnimation
        } else {
            emptyAnimation
        }
    }

    fun setFull(full: Boolean) {
        this.full = full
    }

    companion object {
        private val fullAnimation = arrayOf("full")
        private val emptyAnimation = arrayOf("empty")
        private var i = 0
        private val hearts = arrayOfNulls<GuiHeart>(5)
        fun clear() {
            i = 0
        }
    }
}