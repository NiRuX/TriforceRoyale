package zelda.enemy.armos

import zelda.collision.Hittable
import zelda.collision.Weapon
import zelda.enemy.AttackBehavior
import zelda.enemy.Behavior
import zelda.engine.Game
import zelda.karacter.Direction
import zelda.karacter.Karacter

/**
 *
 * @author Christiaan
 */
class ArmosKnight(game: Game?, x: Int, y: Int, direction: Direction?) :
    Karacter(game, x, y, 32, 52, direction!!, "images/armos.png"), Hittable {
    var behavior: Behavior
    protected var inputInterval: Long = 40
    protected var lastInput: Long = java.lang.System.currentTimeMillis()
    protected var lastHit: Long = java.lang.System.currentTimeMillis()

    init {
        spriteLoc.put("1", Rectangle(0, 0, 32, 52))
        spriteLoc.put("2", Rectangle(32, 0, 32, 52))
        spriteLoc.put("3", Rectangle(64, 0, 32, 52))
        spriteLoc.put("4", Rectangle(96, 0, 32, 52))
        spriteLoc.put("5", Rectangle(128, 0, 32, 52))
        spriteLoc.put("6", Rectangle(160, 0, 32, 52))
        spriteLoc.put("7", Rectangle(192, 0, 32, 52))
        spriteLoc.put("8", Rectangle(224, 0, 32, 52))
        spriteLoc.put("9", Rectangle(256, 0, 32, 52))
        spriteLoc.put("10", Rectangle(288, 0, 32, 52))
        spriteLoc.put("hit 1", Rectangle(0, 64, 32, 52))
        spriteLoc.put("hit 2", Rectangle(32, 64, 32, 52))
        spriteLoc.put("hit 3", Rectangle(64, 64, 32, 52))
        spriteLoc.put("hit 4", Rectangle(96, 64, 32, 52))
        spriteLoc.put("hit 5", Rectangle(128, 64, 32, 52))
        spriteLoc.put("hit 6", Rectangle(160, 64, 32, 52))
        spriteLoc.put("hit 7", Rectangle(192, 64, 32, 52))
        spriteLoc.put("hit 8", Rectangle(224, 64, 32, 52))
        spriteLoc.put("hit 9", Rectangle(256, 64, 32, 52))
        spriteLoc.put("hit 10", Rectangle(288, 64, 32, 52))
        sprite.setSprite(spriteLoc.get("1"))
        health = 25
        state = AttackState(this)
        behavior = AttackBehavior(this)
    }

    override fun hitBy(weapon: Weapon?) {
        if (health >= 25) {
            game.playFx("sounds/enemyHit.mp3")
        }
        when (weapon) {
            Weapon.SWORD -> if (health > 0 && java.lang.System.currentTimeMillis() > lastHit + 800) {
                lastHit = java.lang.System.currentTimeMillis()
                health -= 3
                setState(TransState(this, game.getLink().direction))
            }
            Weapon.BOMB -> if (health > 0 && java.lang.System.currentTimeMillis() > lastHit + 800) {
                lastHit = java.lang.System.currentTimeMillis()
                health -= 10
                setState(TransState(this, game.getLink().direction))
            }
            Weapon.ARROW -> if (health > 0 && java.lang.System.currentTimeMillis() > lastHit + 800) {
                lastHit = java.lang.System.currentTimeMillis()
                health -= 1
                setState(TransState(this, game.getLink().direction))
            }
        }
        if (health <= 0) {
            alive = false
            game.playFx("sounds/enemyDie.mp3")
            randomGoodie()
        }
    }

    public override fun preAnimation() {
        state!!.handleAnimation()
    }

    override fun doInLoop() {
        if (java.lang.System.currentTimeMillis() > lastInput + inputInterval) {
            state!!.handleInput()
            behavior.behave()
            lastInput = java.lang.System.currentTimeMillis()
        }
    }
}