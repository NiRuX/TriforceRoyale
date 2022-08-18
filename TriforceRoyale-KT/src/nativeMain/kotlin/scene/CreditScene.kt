/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zelda.scene

import zelda.engine.Game

/**
 *
 * @author Christiaan
 */
class CreditScene(game: Game) : ZeldaScene(game, "images/aftitel.png", "CreditScene") {
    init {
        if (!game.song.equals("sounds/credits.mp3")) {
            game.stopMusic()
            game.playMusic("sounds/credits.mp3", true)
        }
    }

    fun handleSwitchScene(exit: Rectangle?) {}
    fun handleSwitchScene(entrance: String?) {}
}