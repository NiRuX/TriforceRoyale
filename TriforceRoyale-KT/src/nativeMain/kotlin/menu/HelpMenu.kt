package zelda.menu

import zelda.engine.Game
import zelda.engine.Scene

/**
 *
 * @author maartenhus
 */
class HelpMenu(game: Game) : Scene(game, "images/help-menu.png", "HelpMenu") {
    private val inputInterval: Long = 100
    private var lastInput: Long = java.lang.System.currentTimeMillis()

    init {
        sprite.setSprite(Rectangle(0, 0, game.width, game.height))
        try {
            game.stopMusic()
        } catch (e: java.lang.Exception) {
        }
        game.playMusic("sounds/help-menu.mp3", false)
    }

    override fun handleInput() {
        if (java.lang.System.currentTimeMillis() > lastInput + inputInterval) {
            if (game.isEnterPressed) {
                game.setScene(MainMenu(game))
            }
            lastInput = java.lang.System.currentTimeMillis()
        }
    }
}