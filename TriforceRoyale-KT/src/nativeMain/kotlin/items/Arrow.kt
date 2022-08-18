package zelda.items

import zelda.collision.Hittable
import zelda.collision.Weapon
import zelda.engine.GObject
import zelda.engine.Game
import zelda.karacter.Direction
import zelda.link.Link

/**
 *
 * @author frankie
 */
class Arrow(game: Game, x: Int, y: Int) : GObject(game, x, y, 13, 4, "images/arrows.png") {
    private var hit = false
    private val direction: Direction

    init {

        // Arrow Direction only
        spriteLoc.put("arrowRight", Rectangle(75, 0, 17, 6))
        spriteLoc.put("arrowLeft", Rectangle(50, 0, 17, 6))
        spriteLoc.put("arrowDown", Rectangle(0, 0, 6, 17))
        spriteLoc.put("arrowUp", Rectangle(25, 0, 6, 17))
        spriteLoc.put("arrowDown1", Rectangle(0, 25, 7, 12))
        spriteLoc.put("arrowDown2", Rectangle(25, 25, 7, 12))
        spriteLoc.put("arrowDown3", Rectangle(50, 25, 7, 12))
        spriteLoc.put("arrowUp1", Rectangle(0, 50, 7, 12))
        spriteLoc.put("arrowUp2", Rectangle(25, 50, 7, 12))
        spriteLoc.put("arrowUp3", Rectangle(50, 50, 7, 12))
        spriteLoc.put("arrowRight1", Rectangle(0, 75, 12, 7))
        spriteLoc.put("arrowRight2", Rectangle(25, 75, 12, 7))
        spriteLoc.put("arrowRight3", Rectangle(50, 75, 12, 7))
        spriteLoc.put("arrowLeft1", Rectangle(0, 100, 12, 7))
        spriteLoc.put("arrowLeft2", Rectangle(25, 100, 12, 7))
        spriteLoc.put("arrowLeft3", Rectangle(50, 100, 12, 7))
        animationInterval = 140
        direction = game.getLink().getDirection()
        when (direction) {
            UP -> {
                sprite.setSprite(spriteLoc.get("arrowUp"))
                animation = arrowUp
                height = 13
                width = 4
            }
            DOWN -> {
                sprite.setSprite(spriteLoc.get("arrowDown"))
                animation = arrowDown
                height = 13
                width = 4
            }
            LEFT -> {
                sprite.setSprite(spriteLoc.get("arrowLeft"))
                animation = arrowLeft
            }
            RIGHT -> {
                sprite.setSprite(spriteLoc.get("arrowRight"))
                animation = arrowRight
            }
        }
        game.playFx("sounds/bowArrow.mp3")
    }

    override fun doInLoop() {
        // if arrow moves outside of the scene it should die.
        if (x > game.getScene()!!.getSprite().width || y > game.getScene()!!.getSprite().width || x < 0 || y < 0) {
            alive = false
            return
        }
        when (direction) {
            UP -> setY(getY() - SPEED)
            DOWN -> setY(getY() + SPEED)
            LEFT -> setX(getX() - SPEED)
            RIGHT -> setX(getX() + SPEED)
        }
    }

    public override fun preAnimation() {
        if (hit) {
            liquid = true
            if (animationCounter == animation.size) {
                isAlive = false
            }
        }
    }

    public override fun wallCollision() {
        arrowHitSomething()
    }

    public override fun collision(obj: GObject?) {
        if (obj is Hittable && obj !is Link && obj !is Bush) {
            val hittable = obj as Hittable
            hittable.hitBy(Weapon.ARROW)
            alive = false
            arrowHitSomething()
        }
        if (obj is Guard) {
            arrowHitSomething()
        }
        if (obj is Bush) {
            when (direction) {
                UP -> setYHardCore(getY() - SPEED)
                DOWN -> setYHardCore(getY() + SPEED)
                LEFT -> setXHardCore(getX() - SPEED)
                RIGHT -> setXHardCore(getX() + SPEED)
            }
        }
    }

    private fun arrowHitSomething() {
        // Arrow wiggel animation
        when (direction) {
            UP -> animation = arrowHitUp
            DOWN -> animation = arrowHitDown
            LEFT -> animation = arrowHitLeft
            RIGHT -> animation = arrowHitRight
        }
        hit = true
    }

    companion object {
        //Arrow animation at the moment only one animation
        private val arrowRight = arrayOf("arrowRight")
        private val arrowLeft = arrayOf("arrowLeft")
        private val arrowDown = arrayOf("arrowDown")
        private val arrowUp = arrayOf("arrowUp")
        private val arrowHitDown =
            arrayOf("arrowDown1", "arrowDown2", "arrowDown3", "arrowDown1", "arrowDown2", "arrowDown3")
        private val arrowHitUp = arrayOf("arrowUp1", "arrowUp2", "arrowUp3", "arrowUp1", "arrowUp2", "arrowUp3")
        private val arrowHitLeft =
            arrayOf("arrowLeft1", "arrowLeft2", "arrowLeft3", "arrowLeft1", "arrowLeft2", "arrowLeft3")
        private val arrowHitRight =
            arrayOf("arrowRight1", "arrowRight2", "arrowRight3", "arrowRight1", "arrowRight2", "arrowRight3")
        private const val SPEED = 3
    }
}