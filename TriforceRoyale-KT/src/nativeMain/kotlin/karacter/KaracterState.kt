package zelda.karacter

/**
 * This is the superclass for states.
 *
 * @author maartenhus
 */
abstract class KaracterState(protected var karacter: Karacter) {
    protected var name: String? = null

    init {
        karacter.resetAnimationCounter()
    }

    fun handleInput() {}
    fun handleAnimation() {}
    override fun toString(): String {
        return name!!
    }
}