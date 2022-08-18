package zelda.enemy

import zelda.karacter.Direction
import zelda.karacter.Karacter
import zelda.karacter.KaracterState

/**
 *
 * @author maartenhus
 */
class StandState(soldier: Karacter) : KaracterState(soldier) {
    private val oldDirection: Direction

    init {
        name = "StandState"
        oldDirection = soldier.direction
        when (oldDirection) {
            Direction.UP -> soldier.animation = upAnimation
            Direction.DOWN -> soldier.animation = downAnimation
            Direction.LEFT -> soldier.animation = leftAnimation
            Direction.RIGHT -> soldier.animation = rightAnimation
        }
    }

    override fun handleInput() {
        //System.out.println("oldDirection " + oldDirection + " new " + karacter.getDirection());
        if (oldDirection !== karacter.getDirection()) {
            karacter.setState(WalkState(karacter))
        }
    }

    companion object {
        private val downAnimation = arrayOf("Stand down")
        private val upAnimation = arrayOf("Stand up")
        private val leftAnimation = arrayOf("Stand left")
        private val rightAnimation = arrayOf("Stand right")
    }
}