package zelda.enemy.armos

import zelda.karacter.Direction
import zelda.karacter.Karacter
import zelda.karacter.KaracterState

/**
 *
 * @author vincentklarholz
 */
class TransState(armosKnight: Karacter?, direction: Direction) : KaracterState(armosKnight!!) {
    private val animationHit =
        arrayOf("hit 1", "hit 2", "hit 3", "hit 4", "hit 5", "hit 6", "hit 7", "hit 8", "hit 9", "hit 10")
    private val direction: Direction

    init {
        name = "TransState"
        karacter.setAnimationInterval(10)
        this.direction = direction
    }

    fun left() {
        karacter.setAnimation(animationHit)
        karacter.setX(karacter.getX() + 8)
    }

    fun right() {
        karacter.setAnimation(animationHit)
        karacter.setX(karacter.getX() - 8)
    }

    fun up() {
        karacter.setAnimation(animationHit)
        karacter.setY(karacter.getY() + 8)
    }

    fun down() {
        karacter.setAnimation(animationHit)
        karacter.setY(karacter.getY() - 8)
    }

    override fun handleAnimation() {
        val animationCounter: Int = karacter.getAnimationCounter()
        if (animationCounter == karacter.getAnimation().length) {
            karacter.setAnimationInterval(90)
            karacter.setState(AttackState(karacter))
        }
        when (direction) {
            Direction.UP -> down()
            Direction.DOWN -> up()
            Direction.LEFT -> right()
            Direction.RIGHT -> left()
        }
    }
}