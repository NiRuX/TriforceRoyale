package zelda.enemy

import zelda.collision.Hittable
import zelda.collision.Weapon
import zelda.engine.Game
import zelda.karacter.Direction

/**
 * A White soldier.
 *
 * @author maartenhus
 */
class GhostSoldier(game: Game?, x: Int, y: Int, direction: Direction?) :
    Soldier(game, x, y, direction, "images/boss.png"), Hittable {
    init {
        behavior = AttackBehavior(this)
        health = 20
    }

    override fun hitBy(weapon: Weapon?) {
        if (health >= 1) {
            game.playFx("sounds/enemyHit.mp3")
        }
        when (weapon) {
            Weapon.SWORD -> if (health > 0 && java.lang.System.currentTimeMillis() > lastHit + 800) {
                lastHit = java.lang.System.currentTimeMillis()
                health -= 3
                setState(TransState(this, game.getLink().getDirection()))
            }
            Weapon.BOMB -> if (health > 0 && java.lang.System.currentTimeMillis() > lastHit + 800) {
                lastHit = java.lang.System.currentTimeMillis()
                health -= 10
                setState(TransState(this, game.getLink().getDirection()))
            }
            Weapon.ARROW -> if (health > 0 && java.lang.System.currentTimeMillis() > lastHit + 800) {
                lastHit = java.lang.System.currentTimeMillis()
                health -= 3
                setBehavior(AttackBehavior(this))
            }
        }
        if (health <= 0) {
            alive = false
            game.playFx("sounds/enemyDie.mp3")
            randomGoodie()
        }
    }
}