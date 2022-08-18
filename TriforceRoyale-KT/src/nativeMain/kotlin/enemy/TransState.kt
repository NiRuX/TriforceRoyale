package zelda.enemy

import zelda.karacter.Direction
import zelda.karacter.Karacter
import zelda.karacter.KaracterState

/**
 *
 * @author vincentklarholz
 */
class TransState(soldier: Karacter?, direction: Direction) : KaracterState(soldier!!) {
    private val direction: Direction

    init {
        name = "TransState"
        karacter.setAnimationInterval(40)
        this.direction = direction
    }

    fun left() {
        karacter.setAnimation(leftAnimation)
        karacter.setX(karacter.getX() + 4)
    }

    fun right() {
        karacter.setAnimation(rightAnimation)
        karacter.setX(karacter.getX() - 4)
    }

    fun up() {
        karacter.setAnimation(upAnimation)
        karacter.setY(karacter.getY() + 4)
    }

    fun down() {
        karacter.setAnimation(downAnimation)
        karacter.setY(karacter.getY() - 4)
    }

    override fun handleAnimation() {
        val animationCounter: Int = karacter.getAnimationCounter()
        if (animationCounter == karacter.getAnimation().length) {
            karacter.setAnimationInterval(90)
            karacter.setState(WalkState(karacter))
        }
        when (direction) {
            Direction.UP -> down()
            Direction.DOWN -> up()
            Direction.LEFT -> right()
            Direction.RIGHT -> left()
        }
    }

    companion object {
        private val downAnimation = arrayOf(
            "Stand down",
            "Stand down",
            "Stand down",
            "Stand down",
            "Stand down",
            "Stand down",
            "Stand down",
            "Stand down",
            "Stand down",
            "Stand down"
        )
        private val upAnimation = arrayOf(
            "Stand up",
            "Stand up",
            "Stand up",
            "Stand up",
            "Stand up",
            "Stand up",
            "Stand up",
            "Stand up",
            "Stand up",
            "Stand up"
        )
        private val leftAnimation = arrayOf(
            "Stand left",
            "Stand left",
            "Stand left",
            "Stand left",
            "Stand left",
            "Stand left",
            "Stand left",
            "Stand left",
            "Stand left",
            "Stand left"
        )
        private val rightAnimation = arrayOf(
            "Stand right",
            "Stand right",
            "Stand right",
            "Stand right",
            "Stand right",
            "Stand right",
            "Stand right",
            "Stand right",
            "Stand right",
            "Stand right"
        )
    }
}