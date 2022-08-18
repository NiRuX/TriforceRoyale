package zelda.enemy

import zelda.engine.Game
import zelda.karacter.Direction
import zelda.karacter.Karacter

/**
 *
 * @author maartenhus
 */
abstract class Soldier(game: Game?, x: Int, y: Int, direction: Direction?, image: String?) :
    Karacter(game, x, y, 10, 20, direction!!, image) {
    var behavior: Behavior? = null
    protected var inputInterval: Long = 50
    protected var lastInput: Long = java.lang.System.currentTimeMillis()
    protected var lastHit: Long = java.lang.System.currentTimeMillis()

    init {
        spriteLoc.put("Stand right", Rectangle(0, 0, 27, 27))
        spriteLoc.put("Walk right 1", Rectangle(30, 0, 32, 27))
        spriteLoc.put("Walk right 2", Rectangle(64, 0, 29, 27))
        spriteLoc.put("Stand left", Rectangle(0, 30, 27, 27))
        spriteLoc.put("Walk left 1", Rectangle(30, 30, 32, 27))
        spriteLoc.put("Walk left 2", Rectangle(64, 30, 29, 27))
        spriteLoc.put("Stand up", Rectangle(0, 60, 22, 24))
        spriteLoc.put("Walk up 1", Rectangle(30, 60, 22, 24))
        spriteLoc.put("Walk up 2", Rectangle(60, 60, 22, 25))
        spriteLoc.put("Stand down", Rectangle(0, 90, 22, 33))
        spriteLoc.put("Walk down 1", Rectangle(30, 90, 22, 34))
        spriteLoc.put("Walk down 2", Rectangle(60, 90, 22, 38))
        spriteLoc.put("Walk down 3", Rectangle(0, 125, 22, 35))
        spriteLoc.put("hit right", Rectangle(0, 160, 27, 27))
        spriteLoc.put("hit left", Rectangle(0, 192, 27, 27))
        spriteLoc.put("hit down", Rectangle(0, 256, 22, 33))
        spriteLoc.put("hit up", Rectangle(0, 224, 22, 24))
        sprite.setSprite(spriteLoc.get("Stand right"))
        health = 6
        state = WalkState(this)
    }

    public override fun preAnimation() {
        state!!.handleAnimation()
    }

    override fun doInLoop() {
        if (java.lang.System.currentTimeMillis() > lastInput + inputInterval) {
            state!!.handleInput()
            behavior!!.behave()
            lastInput = java.lang.System.currentTimeMillis()
        }
    }
}