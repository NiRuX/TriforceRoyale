package zelda.scene

import zelda.engine.Game

/**
 *
 * @author Christiaan
 */
class DungeonScene(game: Game, entrance: String?) : ZeldaScene(game, "images/kerker.png", "DungeonScene") {
    var wall2: Polygon
    var wall1: Polygon
    var table: Polygon
    var door: Polygon? = null
    private val zeldaExit: Rectangle = Rectangle(351, 158, 20, 10)

    init {
        exits.add(zeldaExit)
        val dxpos = intArrayOf(
            67,
            66,
            437,
            438,
            401,
            401,
            379,
            376,
            401,
            401,
            323,
            323,
            340,
            341,
            291,
            288,
            448,
            434,
            474,
            467,
            38,
            35,
            71
        )
        val dypos = intArrayOf(
            123,
            280,
            281,
            205,
            204,
            202,
            202,
            167,
            162,
            126,
            126,
            160,
            162,
            200,
            201,
            87,
            85,
            168,
            170,
            329,
            319,
            94,
            96
        )
        wall2 = Polygon(dxpos, dypos, dypos.size)
        val expos = intArrayOf(71, 106, 105, 111, 112, 179, 179, 202, 203, 176, 178, 256, 258, 235, 234, 288, 273, 106)
        val eypos = intArrayOf(96, 97, 122, 122, 204, 203, 202, 201, 164, 162, 125, 123, 163, 164, 201, 199, 89, 88)
        wall1 = Polygon(expos, eypos, eypos.size)
        val fxpos = intArrayOf(129, 129, 159, 160, 130)
        val fypos = intArrayOf(228, 252, 252, 229, 227)
        table = Polygon(fxpos, fypos, fypos.size)

//        int[] rxpos = {77, 99, 100, 80};
//        int[] rypos = {124, 125, 117, 116};
//
//        door = new Polygon(rxpos, rypos, rypos.length);
        solids.add(wall2)
        solids.add(wall1)
        solids.add(table)
        //        solids.add(door);
        gameObjects.add(game.getLink())
        if (!game.song.equals("sounds/castle.mp3")) {
            game.stopMusic()
            game.playMusic("sounds/castle.mp3", true)
        }
        handleSwitchScene(entrance)
    }

    fun handleSwitchScene(exit: Rectangle) {
        if (exit === zeldaExit) {
            game.setScene(CreditScene(game))
        }
    }

    fun handleSwitchScene(entrance: String) {
        if (entrance == "ArmosScene") {
            game.getLink().setXHardCore(81)
            game.getLink().setYHardCore(120)
        }
    }
}