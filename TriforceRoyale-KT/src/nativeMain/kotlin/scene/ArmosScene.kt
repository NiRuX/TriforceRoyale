package zelda.scene

import zelda.enemy.armos.ArmosKnight
import zelda.engine.Game
import zelda.items.Warp
import zelda.karacter.Direction

/**
 *
 * @author frankie
 */
class ArmosScene(game: Game, entrance: String?) :
    ZeldaScene(game, "images/battle-scene.png", "ArmosScene") {
    private val leftTreeline: Polygon
    private val RightTreeline: Polygon
    private val deadTree: Polygon
    private val warpExit: Rectangle = Rectangle(232, 270, 16, 16)
    private val warp = Warp(game, 232, 270)
    private var warpVisible = false

    init {
        exits.add(warpExit)
        val axpos = intArrayOf(
            239,
            4,
            3,
            4,
            240,
            224,
            205,
            199,
            197,
            179,
            175,
            171,
            146,
            107,
            104,
            87,
            77,
            74,
            54,
            70,
            87,
            101,
            109,
            119,
            136,
            170,
            190,
            239,
            3,
            4,
            239,
            223,
            207,
            197,
            200,
            184,
            173,
            173,
            153,
            126,
            109,
            102,
            99,
            81,
            81,
            73,
            57,
            55,
            71,
            80,
            93,
            101,
            126,
            130,
            165,
            197,
            211,
            226,
            241,
            237
        )
        val aypos = intArrayOf(
            4,
            5,
            399,
            509,
            510,
            501,
            484,
            474,
            447,
            434,
            420,
            396,
            386,
            387,
            372,
            346,
            326,
            299,
            281,
            262,
            186,
            166,
            142,
            124,
            99,
            116,
            111,
            4,
            5,
            508,
            510,
            499,
            486,
            475,
            452,
            434,
            424,
            401,
            386,
            391,
            389,
            377,
            349,
            338,
            323,
            298,
            291,
            267,
            243,
            216,
            177,
            162,
            123,
            100,
            110,
            74,
            71,
            71,
            77,
            4
        )
        leftTreeline = Polygon(axpos, aypos, aypos.size)
        val bxpos = intArrayOf(
            237,
            508,
            507,
            241,
            258,
            273,
            283,
            282,
            304,
            327,
            352,
            389,
            402,
            402,
            420,
            427,
            427,
            442,
            449,
            454,
            469,
            476,
            473,
            453,
            435,
            411,
            406,
            399,
            386,
            359,
            322,
            294,
            283,
            276,
            260,
            241,
            235
        )
        val bypos = intArrayOf(
            2,
            4,
            507,
            510,
            503,
            485,
            475,
            449,
            434,
            435,
            435,
            438,
            427,
            401,
            385,
            376,
            354,
            338,
            325,
            300,
            290,
            283,
            264,
            245,
            213,
            165,
            149,
            120,
            120,
            119,
            114,
            96,
            76,
            68,
            67,
            73,
            3
        )
        RightTreeline = Polygon(bxpos, bypos, bypos.size)
        val cxpos = intArrayOf(223, 228, 241, 255, 257, 255, 240, 226, 222, 226)
        val cypos = intArrayOf(494, 484, 479, 482, 492, 505, 509, 507, 496, 484)
        deadTree = Polygon(cxpos, cypos, cypos.size)
        solids.add(leftTreeline)
        solids.add(RightTreeline)
        solids.add(deadTree)
        warp.setActive()
        gameObjects.add(warp)

        // add Link
        gameObjects.add(game.getLink())
        gameObjects.add(ArmosKnight(game, 231, 90, Direction.DOWN))
        gameObjects.add(ArmosKnight(game, 83, 236, Direction.LEFT))
        gameObjects.add(ArmosKnight(game, 422, 251, Direction.RIGHT))
        if (!game.song.equals("sounds/boss-bgm.mp3")) {
            game.stopMusic()
            game.playMusic("sounds/boss-bgm.mp3", true)
        }
        handleSwitchScene(entrance)
    }

    fun inputHook() {
        var dead = true
        for (obj in gameObjects) {
            if (obj is ArmosKnight) {
                dead = false
                break
            }
        }
        if (dead && !warpVisible) {
            game.stopMusic()
            game.playMusic("sounds/fanfare.mp3", false)
            warp.setActive()
            warpVisible = true
        }
    }

    fun handleSwitchScene(exit: Rectangle) {
        if (exit === warpExit && warpVisible) {
            game.setScene(DungeonScene(game, "ArmosScene"))
        }
    }

    fun handleSwitchScene(entrance: String) {
        if (entrance == "CastleBasementScene") {
            //moveScene(1,19);
            game.getLink().setXHardCore(251)
            game.getLink().setYHardCore(298)
        }
    }
}