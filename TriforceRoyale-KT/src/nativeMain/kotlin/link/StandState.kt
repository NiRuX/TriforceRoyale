package zelda.link

import zelda.karacter.Direction

/**
 * State for when link is standing still.
 *
 * @author maartenhus
 */
class StandState(link: Link) : LinkState(link) {
    init {
        name = "StandState"
        when (link.direction) {
            Direction.UP -> link.animation = upAnimation
            Direction.DOWN -> link.animation = downAnimation
            Direction.LEFT -> link.animation = leftAnimation
            Direction.RIGHT -> link.animation = rightAnimation
        }
    }

    override fun handleInput() {
        if (game.isjPressed()) {
            link.setState(SwordState(link))
        } else if (game.islPressed()) {
            link.dropBomb()
        } else if (game.iskPressed()) {
            link.shootArrow()
        } else {
            if (link.moveinput()) link.setState(WalkState(link))
        }
    }

    companion object {
        private val downAnimation = arrayOf("Link stand down")
        private val upAnimation = arrayOf("Link stand up")
        private val leftAnimation = arrayOf("Link stand left")
        private val rightAnimation = arrayOf("Link stand right")
    }
}