package zelda.items

import zelda.enemy.Behavior

/**
 *
 * @author vincentklarholz
 */
class BombBehavior(private val bomb: Bomb) : Behavior() {
    private val animationInterval: Long = 50
    private var lastAnimation: Long = java.lang.System.currentTimeMillis()
    private var ticks = 0
    fun behave() {
        if (java.lang.System.currentTimeMillis() > lastAnimation + animationInterval) // if it time to reanimate
        {
            if (ticks == 49) {
                bomb.isAlive = false
            }
            lastAnimation = java.lang.System.currentTimeMillis()
            ticks++
        }
    }
}