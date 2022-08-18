package zelda.karacter

import zelda.engine.GObject
import zelda.engine.Game

/**
 * A GObject that has a state and a direction.
 *
 * @author maartenhus
 */
abstract class Karacter(
    game: Game?,
    x: Int,
    y: Int,
    width: Int,
    height: Int,
    var direction: Direction,
    image: String?
) :
    GObject(game!!, x, y, width, height, image) {
    protected var state: KaracterState? = null
    var health = 1

    fun getState(): KaracterState? {
        return state
    }

    val stateString: String
        get() = state.toString()

    fun setState(state: KaracterState?) {
        this.state = state
    }
}