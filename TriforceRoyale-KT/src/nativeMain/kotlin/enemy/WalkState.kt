package zelda.enemy

import zelda.karacter.Direction
import zelda.karacter.Karacter
import zelda.karacter.KaracterState

/**
 *
 * @author maartenhus
 */
class WalkState(soldier: Karacter?) : KaracterState(soldier!!) {
    private val downAnimation = arrayOf("Stand down", "Walk down 1", "Walk down 2", "Walk down 3")
    private val upAnimation = arrayOf("Stand up", "Walk up 1", "Walk up 2")
    private val leftAnimation = arrayOf("Stand left", "Walk left 1", "Walk left 2")
    private val rightAnimation = arrayOf("Stand right", "Walk right 1", "Walk right 2")
    private val oldX: Int
    private val oldY: Int
    private val oldAnimationInterval: Long

    init {
        name = "WalkState"
        karacter.setAnimationInterval(90)
        oldAnimationInterval = karacter.getAnimationInterval()
        oldX = karacter.getX()
        oldY = karacter.getY()
    }

    override fun handleInput() {
        when (karacter.getDirection()) {
            UP -> up()
            DOWN -> down()
            LEFT -> left()
            RIGHT -> right()
        }
    }

    fun left() {
        if (karacter.getAnimation() !== leftAnimation) {
            karacter.setAnimation(leftAnimation)
        }
        if (karacter.getDirection() !== Direction.LEFT) {
            karacter.setDirection(Direction.LEFT)
        }
        karacter.setWidth(29)
        karacter.setX(karacter.getX() - WALK_SPEED)
    }

    fun right() {
        if (karacter.getAnimation() !== rightAnimation) {
            karacter.setAnimation(rightAnimation)
        }
        if (karacter.getDirection() !== Direction.RIGHT) {
            karacter.setDirection(Direction.RIGHT)
        }
        karacter.setWidth(29)
        karacter.setX(karacter.getX() + WALK_SPEED)
    }

    fun up() {
        if (karacter.getAnimation() !== upAnimation) {
            karacter.setAnimation(upAnimation)
        }
        if (karacter.getDirection() !== Direction.UP) {
            karacter.setDirection(Direction.UP)
        }
        karacter.setWidth(22)
        karacter.setY(karacter.getY() - WALK_SPEED)
    }

    fun down() {
        if (karacter.getAnimation() !== downAnimation) {
            karacter.setAnimation(downAnimation)
        }
        if (karacter.getDirection() !== Direction.DOWN) {
            karacter.setDirection(Direction.DOWN)
        }
        karacter.setWidth(22)
        karacter.setY(karacter.getY() + WALK_SPEED)
    }

    override fun handleAnimation() {
        val animationCounter: Int = karacter.getAnimationCounter()
        val dir: Direction = karacter.getDirection()
        if (animationCounter == karacter.getAnimation().length) {
        } else {
            if (dir === Direction.LEFT) {
                when (animationCounter) {
                    0 -> {}
                    1 -> karacter.setX(karacter.getX() - 5)
                    2 -> karacter.setX(karacter.getX() + 5)
                }
            }
        }
    }

    companion object {
        private const val WALK_SPEED = 2
    }
}