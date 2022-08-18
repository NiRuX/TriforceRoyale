package zelda.link

import zelda.karacter.Direction

/**
 *
 * @author vincentklarholz
 */
class TransState(link: Link, direction: Direction) : LinkState(link) {
    private val direction: Direction

    init {
        name = "TransState"
        link.animationInterval = 10
        this.direction = direction
    }

    fun left() {
        link.setAnimation(leftAnimation)
        link.setX(link.getX() + 4)
    }

    fun right() {
        link.setAnimation(rightAnimation)
        link.setX(link.getX() - 4)
    }

    fun up() {
        link.setAnimation(upAnimation)
        link.setY(link.getY() + 4)
    }

    fun down() {
        link.setAnimation(downAnimation)
        link.setY(link.getY() - 4)
    }

    override fun handleAnimation() {
        val animationCounter: Int = link.getAnimationCounter()
        if (animationCounter == link.getAnimation().length) {
            link.setAnimationInterval(90)
            link.setState(WalkState(link))
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
            "Link stand down",
            "Link stand down",
            "Link stand down",
            "Link stand down",
            "Link stand down",
            "Link stand down",
            "Link stand down",
            "Link stand down",
            "Link stand down",
            "Link stand down"
        )
        private val upAnimation = arrayOf(
            "Link stand up",
            "Link stand up",
            "Link stand up",
            "Link stand up",
            "Link stand up",
            "Link stand up",
            "Link stand up",
            "Link stand up",
            "Link stand up",
            "Link stand up"
        )
        private val leftAnimation = arrayOf(
            "Link stand left",
            "Link stand left",
            "Link stand left",
            "Link stand left",
            "Link stand left",
            "Link stand left",
            "Link stand left",
            "Link stand left",
            "Link stand left",
            "Link stand left"
        )
        private val rightAnimation = arrayOf(
            "Link stand right",
            "Link stand right",
            "Link stand right",
            "Link stand right",
            "Link stand right",
            "Link stand right",
            "Link stand right",
            "Link stand right",
            "Link stand right",
            "Link stand right"
        )
    }
}