package zelda.link

import zelda.engine.Game
import zelda.karacter.KaracterState

/**
 * Superclass for link's state.
 *
 * @author maartenhus
 */
open class LinkState(protected var link: Link) : KaracterState(link) {
    protected var game: Game

    init {
        game = link.game
    }
}