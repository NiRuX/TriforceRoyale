package zelda.link

import zelda.items.Arrow
import zelda.karacter.Direction

/**
 * State for when link is using the bow.
 *
 * @author frankie
 */
class BowState(link: Link) : LinkState(link) {
    private val oldX: Int
    private val oldY: Int

    init {
        name = "BowState"
        oldX = link.getX()
        oldY = link.getY()
        link.setCheckcollision(false)
        when (link.getDirection()) {
            UP -> {
                link.setAnimation(upAnimation)
                game.getScene().addNewGObject(Arrow(game, link.getX() + 7, link.getY()))
            }
            DOWN -> {
                link.setAnimation(downAnimation)
                game.getScene().addNewGObject(Arrow(game, link.getX() + 7, link.getY()))
            }
            LEFT -> {
                link.setAnimation(leftAnimation)
                game.getScene().addNewGObject(Arrow(game, link.getX(), link.getY() + 9))
            }
            RIGHT -> {
                link.setAnimation(rightAnimation)
                game.getScene().addNewGObject(Arrow(game, link.getX(), link.getY() + 9))
            }
        }
    }

    fun handleAnimation() {
        val animationCounter: Int = link.getAnimationCounter()
        if (animationCounter == link.getAnimation().length) {
            link.setY(oldY)
            link.setX(oldX)
            link.setCheckcollision(true)
            link.setState(StandState(link))
        } else {
            val dir: Direction = link.getDirection()
            if (dir === Direction.UP) {
                when (animationCounter) {
                    0 -> link.setX(link.getX() - 2)
                    1 -> link.setX(link.getX() - 3)
                }
            } else if (dir === Direction.LEFT) {
                when (animationCounter) {
                    0 -> {
                        link.setX(link.getX() + 2)
                        link.setY(link.getY() + 1)
                    }
                    1 -> link.setX(link.getX() - 2)
                }
            }
        }
    }

    companion object {
        private val downAnimation = arrayOf("Link bow down 1", "Link bow down 2", "Link bow down 3")
        private val upAnimation = arrayOf("Link bow up 1", "Link bow up 2", "Link bow up 3")
        private val leftAnimation = arrayOf("Link bow left 1", "Link bow left 2", "Link bow left 3")
        private val rightAnimation = arrayOf("Link bow right 1", "Link bow right 2")
    }
}