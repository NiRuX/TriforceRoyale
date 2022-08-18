package zelda.items

import zelda.collision.Hittable
import zelda.collision.Weapon
import zelda.enemy.Behavior
import zelda.engine.GObject
import zelda.engine.Game

/**
 *
 * @author vincentklarholz
 */
class Bomb(game: Game?, x: Int, y: Int) : GObject(game!!, x, y, 13, 16, "images/bombs.png") {
    private val behavior: Behavior
    private var bomb: Rectangle? = null

    init {
        spriteLoc.put("bomb1", Rectangle(0, 0, 13, 16))
        spriteLoc.put("bomb2", Rectangle(13, 0, 13, 16))
        spriteLoc.put("bomb3", Rectangle(26, 0, 13, 16))
        spriteLoc.put("bomb4", Rectangle(39, 0, 13, 16))
        spriteLoc.put("bomb5", Rectangle(52, 0, 13, 16))
        spriteLoc.put("bomb6", Rectangle(65, 0, 13, 16))
        spriteLoc.put("bomb7", Rectangle(78, 0, 13, 16))
        spriteLoc.put("bomb8", Rectangle(91, 0, 13, 16))
        spriteLoc.put("bomb9", Rectangle(104, 0, 13, 16))
        spriteLoc.put("bomb10", Rectangle(117, 0, 30, 30))
        sprite.setSprite(spriteLoc.get("bomb1"))
        animation = bombAnimation
        animationInterval = 50 //keep on 50 for 2.5 sec bomb countdown.
        liquid = true
        checkcollision = false
        behavior = BombBehavior(this)
    }

    public override fun preAnimation() {
        if (animationCounter == 1) {
            game.playFx("sounds/linkBounce.mp3")
        }
        if (animationCounter == 48) {
            game.playFx("sounds/bombExplode.mp3")
            x -= 8
            y -= 6
            bomb = Rectangle(x, y, 30, 30)
            game.getScene()!!.addHitter(bomb)
        }
    }

    override fun doInLoop() {
        behavior.behave()
        if (animationCounter > 48) {
            for (obj in game.getScene()!!.gObjects) {
                val area: java.awt.geom.Area = java.awt.geom.Area()
                area.add(java.awt.geom.Area(bomb))
                area.intersect(java.awt.geom.Area(obj.rectangle))
                if (obj is Hittable && !area.isEmpty() && this !== obj) {
                    val hittable = obj as Hittable
                    hittable.hitBy(Weapon.BOMB)
                }
            }
            game.getScene()!!.removeHitter(bomb)
        }
    }

    companion object {
        //Bomb animation is 2.5 sec so 50 items in array.
        private val bombAnimation = arrayOf(
            "bomb1", "bomb1", "bomb1", "bomb1", "bomb1", "bomb1", "bomb1", "bomb2", "bomb3", "bomb4",
            "bomb1", "bomb1", "bomb1", "bomb1", "bomb1", "bomb1", "bomb2", "bomb3", "bomb4", "bomb1",
            "bomb1", "bomb1", "bomb1", "bomb1", "bomb2", "bomb3", "bomb4", "bomb1", "bomb1", "bomb1",
            "bomb1", "bomb2", "bomb3", "bomb4", "bomb2", "bomb3", "bomb2", "bomb8", "bomb4", "bomb2",
            "bomb3", "bomb2", "bomb8", "bomb4", "bomb8", "bomb4", "bomb9", "bomb9", "bomb10", "bomb10", "bomb10"
        )
    }
}