package zelda.link

import zelda.collision.Hittable
import zelda.collision.Weapon
import zelda.karacter.Direction

/**
 * State for when link is swinging his sword.
 *
 * @author maartenhus
 */
class SwordState(link: Link) : LinkState(link) {
    private var sword: Rectangle? = null
    private val oldX: Int
    private val oldY: Int
    private val oldAnimationInterval: Long

    init {
        name = "SwordState"
        oldX = link.getX()
        oldY = link.getY()
        oldAnimationInterval = link.animationInterval
        link.isCheckcollision = false
        when (link.direction) {
            Direction.UP -> {
                link.animation = upAnimation
                link.animationInterval = 20
                sword = Rectangle(oldX - 10, oldY - 10, 30, 10)
                game.playFx("sounds/swordSlash1.mp3")
            }
            Direction.DOWN -> {
                link.animation = downAnimation
                link.animationInterval = 30
                sword = Rectangle(oldX, oldY + link.height, 25, 10)
                game.playFx("sounds/swordSlash2.mp3")
            }
            Direction.LEFT -> {
                link.animation = leftAnimation
                link.animationInterval = 20
                sword = Rectangle(oldX - 10, oldY, 20, 30)
                game.playFx("sounds/swordSlash3.mp3")
            }
            Direction.RIGHT -> {
                link.animation = rightAnimation
                link.animationInterval = 20
                sword = Rectangle(oldX + link.width, oldY, 13, 28)
                game.playFx("sounds/swordSlash4.mp3")
            }
        }
        game.getScene()!!.addHitter(sword)
    }

    override fun handleInput() {
        for (obj in link.getGame().getScene().getGObjects()) {
            val area: java.awt.geom.Area = java.awt.geom.Area()
            area.add(java.awt.geom.Area(sword))
            area.intersect(java.awt.geom.Area(obj.rectangle))
            if (obj is Hittable && !area.isEmpty() && link != obj) {
                val hittable = obj as Hittable
                hittable.hitBy(Weapon.SWORD)
            }
        }
    }

    override fun handleAnimation() {
        val animationCounter: Int = link.getAnimationCounter()

        //System.out.println("Animation Counter is " + animationCounter);

        //sword is done swinging revert back to former state
        if (animationCounter == link.getAnimation().length) {
            link.setY(oldY)
            link.setX(oldX)
            link.setAnimationInterval(oldAnimationInterval)
            link.setCheckcollision(true)
            link.setState(StandState(link))
            game.getScene()!!.removeHitter(sword)
        } else {
            // This section of the code corrects the position of link when he's striking.
            // If you don't do this link appears to be moving when he swings his sword.
            // Go ahead and remove the entire body of this else statement. You'll see what i mean.
            val dir: Direction = link.getDirection()
            if (dir === Direction.UP) {
                when (animationCounter) {
                    0 -> link.setY(link.getY() + 1)
                    2 -> link.setY(link.getY() - 2)
                    3 -> link.setY(link.getY() - 6)
                    4 -> link.setY(link.getY() - 1)
                    6 -> {
                        link.setY(link.getY() + 2)
                        link.setX(link.getX() - 4)
                    }
                    7 -> {
                        link.setY(link.getY() + 2)
                        link.setX(link.getX() - 6)
                    }
                    8 -> {
                        link.setY(link.getY() + 3)
                        link.setX(link.getX() - 2)
                    }
                }
            } else if (dir === Direction.LEFT) {
                when (animationCounter) {
                    0 -> {
                        link.setY(link.getY() - 1)
                        link.setX(link.getX() + 3)
                    }
                    1 -> link.setX(link.getX() - 2)
                    2 -> {
                        link.setY(link.getY() - 1)
                        link.setX(link.getX() - 5)
                    }
                    3 -> link.setX(link.getX() - 2)
                    4 -> {
                        link.setY(link.getY() + 2)
                        link.setX(link.getX() - 4)
                    }
                    6 -> link.setX(link.getX() + 1)
                    8 -> link.setX(link.getX() + 6)
                }
            } else if (dir === Direction.DOWN) {
                when (animationCounter) {
                    0 -> link.setX(link.getX() - 4)
                    1 -> link.setX(link.getX() - 1)
                    2 -> link.setX(link.getX() + 1)
                }
            }
        }
    }

    companion object {
        private val downAnimation = arrayOf(
            "Link sword down 1",
            "Link sword down 2",
            "Link sword down 3",
            "Link sword down 4",
            "Link sword down 5",
            "Link sword down 6"
        )
        private val upAnimation = arrayOf(
            "Link sword up 1",
            "Link sword up 2",
            "Link sword up 3",
            "Link sword up 4",
            "Link sword up 5",
            "Link sword up 6",
            "Link sword up 7",
            "Link sword up 8",
            "Link sword up 9"
        )
        private val leftAnimation = arrayOf(
            "Link sword left 1",
            "Link sword left 2",
            "Link sword left 3",
            "Link sword left 4",
            "Link sword left 5",
            "Link sword left 6",
            "Link sword left 7",
            "Link sword left 8",
            "Link sword left 9"
        )
        private val rightAnimation = arrayOf(
            "Link sword right 1",
            "Link sword right 2",
            "Link sword right 3",
            "Link sword right 4",
            "Link sword right 5",
            "Link sword right 6",
            "Link sword right 7",
            "Link sword right 8"
        )
    }
}