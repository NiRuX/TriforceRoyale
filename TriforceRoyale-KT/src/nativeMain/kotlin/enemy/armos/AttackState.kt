/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zelda.enemy.armos

import zelda.karacter.Karacter
import zelda.karacter.KaracterState

/**
 *
 * @author Christiaan
 */
class AttackState(armosKnight: Karacter) : KaracterState(armosKnight) {
    private val animation = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
    private val oldX: Int
    private val oldY: Int
    private val oldAnimationInterval: Long

    init {
        name = "AttackState"
        armosKnight.animationInterval = 90
        oldAnimationInterval = armosKnight.animationInterval
        oldX = armosKnight.getX()
        oldY = armosKnight.getY()
    }

    override fun handleInput() {
        when (karacter.getDirection()) {
            UP -> animation()
            DOWN -> animation()
            LEFT -> animation()
            RIGHT -> animation()
        }
    }

    fun animation() {
        karacter.setAnimation(animation)
    }

    companion object {
        private const val WALK_SPEED = 5
    }
}