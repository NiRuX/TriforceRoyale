package zelda.scene

import zelda.enemy.WhiteSoldier
import zelda.engine.Game
import zelda.items.Rupee
import zelda.karacter.Direction

/**
 *
 * @author Christiaan
 */
class HiddenScene(game: Game, entrance: String?) :
    ZeldaScene(game, "images/hiddenpath.png", "HiddenScene") {
    var muur: Polygon
    var muur1: Polygon
    var muur2: Polygon
    var muur3: Polygon
    var uitgang: Polygon
    private val exitDown: Rectangle = Rectangle(116, 449, 20, 20)

    init {
        exits.add(exitDown)
        val hxpos = intArrayOf(385, 446, 446, 112, 112, 111, 95, 96, 112, 113, 113, 72, 72, 115, 113, 52, 43, 480, 480)
        val hypos =
            intArrayOf(190, 189, 112, 110, 187, 319, 319, 406, 406, 428, 424, 424, 454, 454, 467, 467, 53, 54, 204)
        muur = Polygon(hxpos, hypos, hypos.size)
        val axpos = intArrayOf(
            143,
            141,
            184,
            183,
            145,
            142,
            157,
            159,
            319,
            320,
            432,
            431,
            431,
            142,
            140,
            144,
            144,
            383,
            384,
            489,
            489,
            240
        )
        val aypos = intArrayOf(
            467,
            455,
            454,
            426,
            423,
            406,
            406,
            350,
            351,
            412,
            414,
            320,
            315,
            313,
            191,
            191,
            142,
            142,
            188,
            299,
            461,
            460
        )
        muur1 = Polygon(axpos, aypos, aypos.size)
        val bxpos = intArrayOf(352, 353, 375, 375, 360, 360)
        val bypos = intArrayOf(314, 371, 372, 359, 358, 318)
        muur2 = Polygon(bxpos, bypos, bypos.size)
        val cxpos = intArrayOf(423, 424, 415, 415, 409, 409, 429)
        val cypos = intArrayOf(320, 365, 364, 358, 358, 372, 371)
        muur3 = Polygon(cxpos, cypos, cypos.size)
        val dxpos = intArrayOf(143, 114, 143)
        val dypos = intArrayOf(467, 467, 466)
        uitgang = Polygon(dxpos, dypos, dypos.size)
        solids.add(muur)
        solids.add(muur1)
        solids.add(muur2)
        solids.add(muur3)
        solids.add(uitgang)
        gameObjects.add(game.getLink())
        gameObjects.add(Rupee(game, 365, 322))
        gameObjects.add(Rupee(game, 373, 322))
        gameObjects.add(Rupee(game, 381, 322))
        gameObjects.add(Rupee(game, 389, 322))
        gameObjects.add(Rupee(game, 397, 322))
        gameObjects.add(Rupee(game, 405, 322))
        gameObjects.add(Rupee(game, 413, 322))
        gameObjects.add(Rupee(game, 365, 336))
        gameObjects.add(Rupee(game, 373, 336))
        gameObjects.add(Rupee(game, 381, 336))
        gameObjects.add(Rupee(game, 389, 336))
        gameObjects.add(Rupee(game, 397, 336))
        gameObjects.add(Rupee(game, 405, 336))
        gameObjects.add(Rupee(game, 413, 336))
        gameObjects.add(WhiteSoldier(game, 123, 117, Direction.UP))
        gameObjects.add(WhiteSoldier(game, 121, 337, Direction.LEFT))
        gameObjects.add(WhiteSoldier(game, 325, 331, Direction.LEFT))
        if (!game.song.equals("sounds/cave.mp3")) {
            game.stopMusic()
            game.playMusic("sounds/cave.mp3", true)
        }
        handleSwitchScene(entrance)
    }

    fun handleSwitchScene(exit: Rectangle) {
        if (exit === exitDown) {
            game.setScene(HyruleScene(game, "HiddenScene"))
        }
    }

    fun handleSwitchScene(entrance: String) {
        if (entrance == "HyruleSceneHatch") {
            moveScene(13, 0)
            game.getLink().setXHardCore(396)
            game.getLink().setYHardCore(141)
        }
        if (entrance == "HyruleSceneStairs") {
            moveScene(1, 79)
            game.getLink().setXHardCore(116)
            game.getLink().setYHardCore(346)
        }
    }
}