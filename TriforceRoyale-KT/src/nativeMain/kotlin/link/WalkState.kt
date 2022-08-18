package zelda.link

import zelda.karacter.Direction

/**
 * State for when link is walking.
 *
 * @author maartenhus
 */
class WalkState(link: Link) : LinkState(link) {
    init {
        name = "WalkState"
        link.animationInterval = 90
    }

    override fun handleInput() {
        if (game.isjPressed()) {
            link.setState(SwordState(link))
        } else {
            if (link.noMoveinput()) {
                link.setState(StandState(link))
            } else if (game.islPressed()) {
                link.dropBomb()
            } else if (game.iskPressed()) {
                link.shootArrow()
            } else {
                if (game.isaPressed()) left()
                if (game.isdPressed()) right()
                if (game.iswPressed()) up()
                if (game.issPressed()) down()
            }
        }
    }

    private fun left() {
        if (link.getAnimation() !== leftAnimation) link.setAnimation(leftAnimation)
        if (link.getDirection() !== Direction.LEFT) link.setDirection(Direction.LEFT)
        link.setX(link.getX() - WALK_SPEED)
    }

    private fun right() {
        if (link.getAnimation() !== rightAnimation) link.setAnimation(rightAnimation)
        if (link.getDirection() !== Direction.RIGHT) link.setDirection(Direction.RIGHT)
        link.setX(link.getX() + WALK_SPEED)
    }

    private fun up() {
        if (link.getAnimation() !== upAnimation) link.setAnimation(upAnimation)
        if (link.getDirection() !== Direction.UP) link.setDirection(Direction.UP)
        link.setY(link.getY() - WALK_SPEED)
    }

    private fun down() {
        if (link.getAnimation() !== downAnimation) link.setAnimation(downAnimation)
        if (link.getDirection() !== Direction.DOWN) link.setDirection(Direction.DOWN)
        link.setY(link.getY() + WALK_SPEED)
    }

    companion object {
        private val downAnimation = arrayOf(
            "Link walk down 1",
            "Link walk down 2",
            "Link walk down 3",
            "Link walk down 6",
            "Link walk down 5",
            "Link walk down 4"
        )
        private val upAnimation = arrayOf(
            "Link walk up 3",
            "Link walk up 2",
            "Link walk up 1",
            "Link walk up 4",
            "Link walk up 6",
            "Link walk up 5"
        )
        private val leftAnimation = arrayOf(
            "Link walk left 3",
            "Link walk left 2",
            "Link walk left 1",
            "Link walk left 4",
            "Link walk left 5",
            "Link walk left 6"
        )
        private val rightAnimation = arrayOf(
            "Link walk right 3",
            "Link walk right 2",
            "Link walk right 1",
            "Link walk right 4",
            "Link walk right 5",
            "Link walk right 6"
        )
        private const val WALK_SPEED = 4
    }
}