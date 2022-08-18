package zelda.scene

import zelda.enemy.BlueSoldier
import zelda.engine.Game
import zelda.items.Rupee
import zelda.items.Warp
import zelda.karacter.Direction

/**
 *
 * @author frankie
 */
class ForrestScene(game: Game, entrance: String?) :
    ZeldaScene(game, "images/forrest-scene.png", "ForrestScene") {
    private val upperTreeline: Polygon
    private val leftTreeline: Polygon
    private val upperTreeline2: Polygon
    private val leftTreeline2: Polygon
    private val middleTreeline: Polygon
    private val entreeWall1: Polygon
    private val entreeWall2: Polygon
    private val lowerWall: Polygon
    private val deadtree1: Polygon
    private val deadtree2: Polygon
    private val deadtree3: Polygon
    private val exitRight1: Rectangle = Rectangle(506, 185, 20, 50)
    private val exitRight2: Rectangle = Rectangle(506, 250, 20, 90)
    private val exitUp: Rectangle = Rectangle(100, 0, 90, 20)
    private val warpExit: Rectangle = Rectangle(393, 108, 16, 16)

    init {
        exits.add(exitRight1)
        exits.add(exitRight2)
        exits.add(exitUp)
        exits.add(warpExit)

        // Draw upperTreeline
        val axpos = intArrayOf(
            507,
            485,
            472,
            451,
            445,
            426,
            412,
            388,
            375,
            354,
            331,
            317,
            291,
            271,
            245,
            219,
            210,
            197,
            508,
            508
        )
        val aypos = intArrayOf(182, 186, 192, 183, 167, 136, 89, 90, 135, 143, 103, 81, 80, 81, 81, 81, 81, 42, 44, 181)
        upperTreeline = Polygon(axpos, aypos, aypos.size)
        val cxpos = intArrayOf(203, 187, 180, 177, 328, 454, 506, 507, 361, 202)
        val cypos = intArrayOf(37, 16, 20, 4, 4, 4, 6, 39, 38, 37)
        upperTreeline2 = Polygon(cxpos, cypos, cypos.size)


        // Draw leftTreeline
        val bxpos = intArrayOf(
            115,
            110,
            93,
            88,
            72,
            96,
            96,
            98,
            117,
            119,
            115,
            110,
            99,
            88,
            73,
            74,
            88,
            96,
            93,
            92,
            87,
            77,
            67,
            5,
            6,
            115
        )
        val bypos = intArrayOf(
            4,
            23,
            52,
            73,
            93,
            114,
            127,
            145,
            162,
            185,
            199,
            213,
            219,
            261,
            271,
            286,
            297,
            310,
            332,
            343,
            360,
            361,
            396,
            398,
            5,
            5
        )
        leftTreeline = Polygon(bxpos, bypos, bypos.size)
        val dxpos = intArrayOf(67, 60, 45, 71, 103, 120, 126, 142, 144, 5, 6, 66)
        val dypos = intArrayOf(398, 408, 429, 436, 435, 451, 482, 497, 509, 506, 398, 399)
        leftTreeline2 = Polygon(dxpos, dypos, dypos.size)
        val expos = intArrayOf(
            378,
            346,
            336,
            321,
            308,
            311,
            311,
            292,
            269,
            245,
            223,
            211,
            189,
            163,
            152,
            157,
            161,
            175,
            180,
            163,
            152,
            157,
            145,
            130,
            128,
            133,
            125,
            117,
            118,
            136,
            156,
            180,
            231,
            255,
            261,
            263,
            251,
            236,
            243,
            276,
            300,
            307,
            313,
            322,
            341,
            347,
            368,
            390,
            397,
            400,
            394,
            379,
            367
        )
        val eypos = intArrayOf(
            179,
            184,
            183,
            164,
            161,
            149,
            132,
            116,
            115,
            114,
            114,
            119,
            114,
            118,
            138,
            162,
            183,
            189,
            207,
            214,
            230,
            254,
            260,
            273,
            293,
            302,
            308,
            357,
            372,
            382,
            376,
            379,
            380,
            372,
            357,
            320,
            308,
            302,
            279,
            286,
            279,
            263,
            238,
            230,
            232,
            245,
            254,
            245,
            231,
            203,
            188,
            179,
            179
        )
        middleTreeline = Polygon(expos, eypos, eypos.size)
        val fxpos = intArrayOf(502, 452, 408, 393, 355, 354, 396, 413, 431, 436, 423, 400, 374, 401, 414, 463, 505, 504)
        val fypos = intArrayOf(236, 235, 280, 280, 317, 340, 383, 384, 362, 325, 340, 346, 320, 293, 293, 244, 246, 234)
        entreeWall1 = Polygon(fxpos, fypos, fypos.size)
        val gxpos = intArrayOf(508, 483, 464, 462, 466, 477, 508, 508)
        val gypos = intArrayOf(345, 344, 329, 342, 363, 382, 385, 345)
        entreeWall2 = Polygon(gxpos, gypos, gypos.size)
        val hxpos = intArrayOf(508, 483, 464, 462, 466, 477, 508, 508, 509, 394, 365, 327, 295, 231, 197, 193, 508, 508)
        val hypos = intArrayOf(345, 344, 329, 342, 363, 382, 385, 345, 442, 443, 411, 408, 440, 443, 474, 510, 508, 441)
        lowerWall = Polygon(hxpos, hypos, hypos.size)
        val ixpos = intArrayOf(507, 477, 474, 505, 507)
        val iypos = intArrayOf(390, 389, 424, 425, 391)
        deadtree1 = Polygon(ixpos, iypos, iypos.size)
        val jxpos = intArrayOf(272, 306, 306, 273, 272)
        val jypos = intArrayOf(322, 322, 352, 353, 322)
        deadtree2 = Polygon(jxpos, jypos, jypos.size)
        val kxpos = intArrayOf(153, 186, 186, 153, 153)
        val kypos = intArrayOf(478, 478, 508, 509, 481)
        deadtree3 = Polygon(kxpos, kypos, kypos.size)
        solids.add(upperTreeline)
        solids.add(leftTreeline)
        solids.add(upperTreeline2)
        solids.add(leftTreeline2)
        solids.add(middleTreeline)
        solids.add(entreeWall1)
        solids.add(entreeWall2)
        solids.add(lowerWall)
        solids.add(deadtree1)
        solids.add(deadtree2)
        solids.add(deadtree3)

        // add Link
        gameObjects.add(game.getLink())
        gameObjects.add(game.getLink())
        gameObjects.add(BlueSoldier(game, 440, 375, Direction.UP, 20))
        gameObjects.add(BlueSoldier(game, 259, 403, Direction.RIGHT, 50))
        gameObjects.add(BlueSoldier(game, 137, 411, Direction.DOWN, 15))
        gameObjects.add(BlueSoldier(game, 128, 110, Direction.UP, 55))
        gameObjects.add(Rupee(game, 380, 110))
        gameObjects.add(Rupee(game, 390, 110))
        gameObjects.add(Rupee(game, 400, 110))
        gameObjects.add(Rupee(game, 410, 110))
        gameObjects.add(Rupee(game, 380, 128))
        gameObjects.add(Rupee(game, 390, 128))
        gameObjects.add(Rupee(game, 400, 128))
        gameObjects.add(Rupee(game, 410, 128))
        gameObjects.add(Warp(game, 393, 108))
        if (!game.song.equals("sounds/overworld.mp3")) {
            game.stopMusic()
            game.playMusic("sounds/overworld.mp3", true)
        }
        handleSwitchScene(entrance)
    }

    fun handleSwitchScene(exit: Rectangle) {
        if (exit === exitRight1) {
            game.setScene(HouseScene(game, "ForrestScene1"))
        }
        if (exit === exitRight2) {
            game.setScene(HouseScene(game, "ForrestScene2"))
        }
        if (exit === exitUp) {
            game.setScene(HyruleScene(game, "ForrestScene3"))
        }
        if (exit === warpExit) {
            game.setScene(BattleScene(game, "warp"))
        }
    }

    fun handleSwitchScene(entrance: String) {
        if (entrance == "HouseSceneLeft1") {
            moveScene(10, 1)
            game.getLink().setXHardCore(462)
            game.getLink().setYHardCore(195)
        }
        if (entrance == "HouseSceneLeft2") {
            moveScene(10, 80)
            game.getLink().setXHardCore(459)
            game.getLink().setYHardCore(200)
        }
        if (entrance == "HyruleScene") {
            moveScene(1, 1)
            game.getLink().setXHardCore(135)
            game.getLink().setYHardCore(31)
        }
        if (entrance == "BattleScene") {
            moveScene(10, 1)
            game.getLink().setXHardCore(382)
            game.getLink().setYHardCore(131)
        }
    }
}