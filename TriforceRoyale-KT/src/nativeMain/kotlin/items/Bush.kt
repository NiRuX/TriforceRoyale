package zelda.items

import zelda.collision.Hittable
import zelda.collision.Weapon
import zelda.engine.GObject
import zelda.engine.Game

/**
 *
 * @author Christiaan
 */
class Bush(game: Game?, x: Int, y: Int) : GObject(game!!, x, y, 16, 14, "images/items.png"),
    Hittable {
    init {
        spriteLoc.put("bush", Rectangle(0, 0, 16, 15))
        spriteLoc.put("stump", Rectangle(17, 0, 16, 15))
        val bushani = arrayOf("bush")
        animation = bushani
        sprite.setSprite(spriteLoc.get("bush"))
    }

    override fun hitBy(weapon: Weapon?) {
        if (weapon === Weapon.SWORD) {
            val bushani = arrayOf("stump")
            animation = bushani
            if (liquid === false) {
                game.playFx("sounds/bushCut.mp3")
                randomGoodie()
            }
            liquid = true
        }
        if (weapon === Weapon.BOMB) {
            val bushani = arrayOf("stump")
            animation = bushani
            if (liquid === false) {
                game.playFx("sounds/bushCut.mp3")
                randomGoodie()
            }
            liquid = true
        }
    }
}