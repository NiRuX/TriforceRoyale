package zelda.enemy

import zelda.karacter.Direction

/**
 *
 * @author Christiaan
 */
class RandomBehavior(soldier: WhiteSoldier) : Behavior() {
    private val soldier: WhiteSoldier
    private val inputInterval: Long = 5000
    private var lastInput: Long = java.lang.System.currentTimeMillis()

    init {
        this.soldier = soldier
    }

    override fun behave() {
        if (java.lang.System.currentTimeMillis() > lastInput + inputInterval) {
            val random: java.util.Random = java.util.Random()
            val r: Int = random.nextInt(3)
            when (r) {
                0 -> soldier.setDirection(Direction.UP)
                1 -> soldier.setDirection(Direction.LEFT)
                2 -> soldier.setDirection(Direction.RIGHT)
                3 -> soldier.setDirection(Direction.DOWN)
            }
            lastInput = java.lang.System.currentTimeMillis()
        }
    }
}