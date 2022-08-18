package zelda.link

import zelda.karacter.Direction
import zelda.scene.HouseScene

/**
 * @author Bas Harteveld
 */
class DeathState(link: Link, direction: Direction?) : LinkState(link) {
    private val deathRightAnimation = arrayOf("Link hit right", "Link death right", "Link death right 2")
    private val deathLeftAnimation = arrayOf("Link hit left", "Link death left", "Link death left 2")
    private val oldAnimationInterval: Long = 0

    init {
        name = "DeathState"
        link.setAnimationInterval(700)
        when (direction) {
            Direction.UP -> link.setAnimation(deathLeftAnimation)
            Direction.DOWN -> link.setAnimation(deathRightAnimation)
            Direction.LEFT -> link.setAnimation(deathLeftAnimation)
            Direction.RIGHT -> link.setAnimation(deathRightAnimation)
        }
    }

    fun handleAnimation() {
        val animationCounter: Int = link.getAnimationCounter()
        if (animationCounter == link.getAnimation().length) {
            link.setAnimationInterval(oldAnimationInterval)
            link.setState(StandState(link))
            link.setHealth(2)
            game.setScene(HouseScene(game, "GameStart"))
        }
    }
}