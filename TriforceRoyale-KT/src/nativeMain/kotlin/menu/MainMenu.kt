package zelda.menu

import zelda.engine.Game
import zelda.engine.Scene
import zelda.scene.HouseScene

/**
 * Code originated from
 * @maartenhus's Github
 */
class MainMenu(game: Game) : Scene(game, "images/main-menu.png", "MainMenu") {
    private val fairy: Fairy = Fairy(game, 210, 215)
    private val inputInterval: Long = 75
    private var lastInput: Long = java.lang.System.currentTimeMillis()

    init {
        sprite.setSprite(Rectangle(0, 0, game.width, game.height))
        gameObjects.add(fairy)
        try {
            game.stopMusic()
        } catch (e: java.lang.Exception) {
        }
        game.playMusic("sounds/main-menu.mp3", false)
    }

    override fun handleInput() {
        if (java.lang.System.currentTimeMillis() > lastInput + inputInterval) {
            checkEnter()
            checkInput()
            setFairy()
            lastInput = java.lang.System.currentTimeMillis()
        }
    }

    private fun checkEnter() {
        if (game.isEnterPressed) {
            when (CURRENT) {
                NEW_GAME -> game.setScene(HouseScene(game, "GameStart"))
                LOAD_GAME -> game.load()
                HELP -> game.setScene(HelpMenu(game))
            }
        }
    }

    private fun checkInput() {
        if (game.issPressed()) {
            if (CURRENT == HELP) {
                CURRENT = NEW_GAME
            } else {
                CURRENT += 1
            }
        }
        if (game.iswPressed()) {
            if (CURRENT == NEW_GAME) {
                CURRENT = HELP
            } else {
                CURRENT -= 1
            }
        }
    }

    private fun setFairy() {
        when (CURRENT) {
            NEW_GAME -> fairy.setY(220)
            LOAD_GAME -> fairy.setY(285)
            HELP -> fairy.setY(348)
        }
    }

    companion object {
        private var CURRENT = 0
        private const val NEW_GAME = 0
        private const val LOAD_GAME = 1
        private const val HELP = 2
    }
}