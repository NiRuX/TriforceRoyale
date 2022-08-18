package zelda.items

import zelda.engine.GObject
import zelda.engine.Game
import zelda.karacter.Direction

/**
 *
 * @author Christiaan
 */
class Guard(game: Game?, x: Int, y: Int, direction: Direction?) :
    GObject(game!!, x, y, 25, 25, "images/items.png") {
    init {
        spriteLoc.put("DOWN", Rectangle(0, 25, 25, 30))
        spriteLoc.put("LEFT", Rectangle(25, 25, 21, 28))
        spriteLoc.put("UP", Rectangle(50, 25, 25, 28))
        spriteLoc.put("RIGHT", Rectangle(75, 25, 21, 28))
        when (direction) {
            UP -> {
                sprite.setSprite(spriteLoc.get("UP"))
                animation = upAnimation
            }
            DOWN -> {
                sprite.setSprite(spriteLoc.get("DOWN"))
                animation = downAnimation
            }
            LEFT -> {
                width = 21
                sprite.setSprite(spriteLoc.get("LEFT"))
                animation = leftAnimation
            }
            RIGHT -> {
                sprite.setSprite(spriteLoc.get("RIGHT"))
                animation = rightAnimation
            }
        }
    }

    companion object {
        private val downAnimation = arrayOf("DOWN")
        private val upAnimation = arrayOf("UP")
        private val leftAnimation = arrayOf("LEFT")
        private val rightAnimation = arrayOf("RIGHT")
    }
}