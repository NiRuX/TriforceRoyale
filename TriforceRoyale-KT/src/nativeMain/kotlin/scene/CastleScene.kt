package zelda.scene

import zelda.enemy.BlueSoldier
import zelda.enemy.WhiteSoldier
import zelda.engine.Game
import zelda.items.Guard
import zelda.items.Heart
import zelda.items.Rupee
import zelda.karacter.Direction

/**
 *
 * @author Christiaan
 */
class CastleScene(game: Game, entrance: String?) : ZeldaScene(game, "images/castle.png", "CastleScene") {
    private val middenbovenmuur: Polygon
    private val stukmuur: Polygon
    private val stukmuur1: Polygon
    private val stukmuur2: Polygon
    private val rechtsonder: Polygon
    private val rechts: Polygon
    private val rechtsmidden: Polygon
    private val linksonder: Polygon
    private val links: Polygon
    private val zuil: Polygon
    private val zuil1: Polygon
    private val zuil2: Polygon
    private val zuil3: Polygon
    private val zuil4: Polygon
    private val zuil5: Polygon
    private val zuil6: Polygon
    private val zuil7: Polygon
    private val midden: Polygon
    private val midden1: Polygon
    private val pot: Polygon
    private val pot1: Polygon
    private val ding1: Polygon
    private val ding: Polygon
    private val ding2: Polygon
    private val ding3: Polygon
    private val exitUp: Rectangle = Rectangle(500, 88, 27, 20)
    private val exitDown: Rectangle = Rectangle(500, 967, 27, 20)

    init {
        exits.add(exitUp)
        exits.add(exitDown)
        val xpos = intArrayOf(
            498,
            352,
            298,
            217,
            215,
            147,
            146,
            145,
            159,
            160,
            190,
            193,
            318,
            320,
            704,
            704,
            830,
            831,
            863,
            865,
            877,
            880,
            672,
            496
        )
        val ypos = intArrayOf(
            561,
            563,
            621,
            621,
            567,
            561,
            477,
            447,
            445,
            175,
            174,
            152,
            152,
            167,
            167,
            152,
            153,
            174,
            174,
            446,
            445,
            476,
            528,
            538
        )
        middenbovenmuur = Polygon(xpos, ypos, ypos.size)
        val axpos = intArrayOf(185, 185, 160, 160, 146, 146)
        val aypos = intArrayOf(568, 627, 628, 591, 589, 564)
        stukmuur = Polygon(axpos, aypos, aypos.size)
        val bxpos = intArrayOf(160, 184, 185, 215, 217, 334, 335, 321, 320, 193, 191, 159)
        val bypos = intArrayOf(666, 665, 717, 715, 665, 663, 697, 695, 757, 757, 743, 743)
        stukmuur1 = Polygon(bxpos, bypos, bypos.size)
        val cxpos = intArrayOf(530, 530, 665, 728, 727, 690, 690, 702, 704, 832, 832, 865, 865, 877, 880, 537)
        val cypos = intArrayOf(537, 557, 559, 622, 661, 662, 694, 694, 756, 756, 735, 734, 592, 591, 476, 508)
        stukmuur2 = Polygon(cxpos, cypos, cypos.size)
        val dxpos = intArrayOf(
            532,
            534,
            592,
            591,
            670,
            702,
            704,
            832,
            832,
            864,
            865,
            927,
            927,
            951,
            953,
            999,
            1001,
            1024,
            1027,
            1037,
            1037,
            997,
            996,
            957,
            956,
            921,
            921,
            871,
            868,
            843,
            837,
            837,
            881,
            839
        )
        val dypos = intArrayOf(
            997,
            959,
            958,
            847,
            846,
            814,
            791,
            791,
            813,
            813,
            853,
            854,
            813,
            814,
            781,
            782,
            813,
            813,
            822,
            822,
            846,
            844,
            787,
            786,
            879,
            882,
            878,
            877,
            881,
            881,
            882,
            981,
            980,
            997
        )
        rechtsonder = Polygon(dxpos, dypos, dypos.size)
        val expos = intArrayOf(
            915,
            915,
            998,
            1000,
            1112,
            1110,
            1074,
            1073,
            1085,
            1086,
            1075,
            1074,
            1108,
            1109,
            1045,
            1047,
            983,
            982,
            985,
            978,
            804,
            805,
            806,
            874,
            873,
            833,
            832,
            704,
            704,
            576,
            574,
            562,
            561,
            544,
            543,
            530,
            529,
            533,
            1007,
            1210,
            1210
        )
        val eypos = intArrayOf(
            996,
            980,
            981,
            879,
            876,
            851,
            850,
            821,
            821,
            656,
            655,
            627,
            624,
            569,
            565,
            425,
            422,
            56,
            50,
            45,
            48,
            72,
            76,
            75,
            102,
            103,
            118,
            118,
            103,
            102,
            110,
            112,
            96,
            96,
            110,
            112,
            95,
            31,
            32,
            399,
            993
        )
        rechts = Polygon(expos, eypos, eypos.size)
        val fxpos = intArrayOf(
            910,
            910,
            953,
            953,
            955,
            1004,
            1004,
            958,
            956,
            995,
            1034,
            1038,
            1040,
            1026,
            1023,
            1000,
            998,
            953,
            949,
            928,
            927,
            913,
            913,
            926,
            926,
            914
        )
        val fypos = intArrayOf(
            102,
            77,
            77,
            465,
            469,
            470,
            570,
            569,
            691,
            692,
            626,
            631,
            653,
            655,
            661,
            663,
            694,
            695,
            662,
            661,
            591,
            591,
            447,
            445,
            103,
            103
        )
        rechtsmidden = Polygon(fxpos, fypos, fypos.size)
        val gxpos = intArrayOf(
            494,
            496,
            430,
            432,
            433,
            352,
            321,
            321,
            192,
            191,
            159,
            159,
            145,
            146,
            154,
            177,
            182,
            182,
            182,
            144
        )
        val gypos = intArrayOf(
            997,
            958,
            956,
            950,
            845,
            844,
            812,
            791,
            792,
            814,
            816,
            852,
            854,
            876,
            878,
            879,
            882,
            952,
            982,
            983
        )
        linksonder = Polygon(gxpos, gypos, gypos.size)
        val hxpos = intArrayOf(
            497,
            495,
            494,
            481,
            479,
            464,
            462,
            450,
            448,
            319,
            319,
            193,
            190,
            177,
            177,
            111,
            110,
            97,
            96,
            110,
            111,
            96,
            97,
            111,
            112,
            70,
            69,
            41,
            41,
            116,
            17,
            18,
            496
        )
        val hypos = intArrayOf(
            100,
            100,
            110,
            110,
            102,
            100,
            111,
            111,
            99,
            98,
            115,
            117,
            108,
            104,
            91,
            90,
            101,
            102,
            445,
            446,
            590,
            591,
            853,
            853,
            875,
            876,
            567,
            568,
            980,
            981,
            994,
            42,
            31
        )
        links = Polygon(hxpos, hypos, hypos.size)
        val ixpos = intArrayOf(545, 558, 546)
        val iypos = intArrayOf(928, 928, 936)
        //
        zuil = Polygon(ixpos, iypos, iypos.size)
        //
        val jxpos = intArrayOf(545, 547, 557, 557)
        val jypos = intArrayOf(896, 903, 904, 897)
        //
        zuil1 = Polygon(jxpos, jypos, jypos.size)
        //
        val kxpos = intArrayOf(547, 547, 556, 557)
        val kypos = intArrayOf(864, 871, 871, 865)
        //
        zuil2 = Polygon(kxpos, kypos, kypos.size)
        //
        val lxpos = intArrayOf(547, 556, 556, 546)
        val lypos = intArrayOf(833, 833, 838, 840)
        //
        zuil3 = Polygon(lxpos, lypos, lypos.size)
        //
        val mxpos = intArrayOf(466, 476, 477, 466, 466)
        val mypos = intArrayOf(833, 832, 840, 840, 832)

//
        zuil4 = Polygon(mxpos, mypos, mypos.size)
        val nxpos = intArrayOf(466, 476, 476, 468, 467)
        val nypos = intArrayOf(864, 865, 869, 870, 865)
        //
        zuil5 = Polygon(nxpos, nypos, nypos.size)
        val oxpos = intArrayOf(466, 476, 477, 466)
        val oypos = intArrayOf(896, 896, 902, 904)
        //
        zuil6 = Polygon(oxpos, oypos, oypos.size)
        //
        val pxpos = intArrayOf(466, 477, 477, 467)
        val pypos = intArrayOf(928, 929, 935, 935)
        zuil7 = Polygon(pxpos, pypos, pypos.size)
        val qxpos = intArrayOf(655, 625, 623, 560, 556, 531, 529, 649, 651, 657, 656)
        val qypos = intArrayOf(693, 693, 623, 622, 652, 652, 597, 597, 665, 670, 693)
        midden = Polygon(qxpos, qypos, qypos.size)
        val rxpos = intArrayOf(368, 369, 399, 401, 463, 466, 492, 496, 494, 372, 371, 366)
        val rypos = intArrayOf(672, 694, 693, 623, 622, 649, 649, 599, 595, 595, 666, 670)
        midden1 = Polygon(rxpos, rypos, rypos.size)
        val sxpos = intArrayOf(434, 461, 462, 451, 452, 428, 428, 418, 417, 430, 433)
        val sypos = intArrayOf(679, 680, 735, 733, 705, 705, 733, 733, 706, 704, 676)
        pot = Polygon(sxpos, sypos, sypos.size)
        val txpos = intArrayOf(385, 394, 393, 384)
        val typos = intArrayOf(717, 718, 735, 735)
        ding = Polygon(txpos, typos, typos.size)
        val uxpos = intArrayOf(562, 562, 570, 572, 591, 594, 595, 605, 605, 596, 589, 590)
        val uypos = intArrayOf(679, 732, 731, 701, 701, 705, 733, 733, 710, 708, 700, 675)
        pot1 = Polygon(uxpos, uypos, uypos.size)
        val vxpos = intArrayOf(628, 628, 636, 637)
        val vypos = intArrayOf(719, 733, 733, 721)
        ding1 = Polygon(vxpos, vypos, vypos.size)
        val wxpos = intArrayOf(481, 494, 494, 482)
        val wypos = intArrayOf(781, 781, 801, 803)
        ding2 = Polygon(wxpos, wypos, wypos.size)
        val xxpos = intArrayOf(531, 542, 542, 531)
        val xypos = intArrayOf(783, 783, 803, 803)
        ding3 = Polygon(xxpos, xypos, xypos.size)
        solids.add(middenbovenmuur)
        solids.add(stukmuur)
        solids.add(stukmuur1)
        solids.add(stukmuur2)
        solids.add(rechtsonder)
        solids.add(rechts)
        solids.add(rechtsmidden)
        solids.add(linksonder)
        solids.add(links)
        solids.add(zuil1)
        solids.add(zuil2)
        solids.add(zuil3)
        solids.add(zuil4)
        solids.add(zuil5)
        solids.add(zuil6)
        solids.add(zuil7)
        solids.add(zuil)
        solids.add(midden)
        solids.add(midden1)
        solids.add(pot)
        solids.add(ding)
        solids.add(pot1)
        solids.add(ding1)
        solids.add(ding2)
        solids.add(ding3)
        gameObjects.add(Rupee(game, 131, 96))
        gameObjects.add(Rupee(game, 148, 96))
        gameObjects.add(Rupee(game, 196, 577))
        gameObjects.add(Rupee(game, 1092, 857))
        gameObjects.add(Rupee(game, 1036, 425))
        gameObjects.add(Heart(game, 812, 57))
        gameObjects.add(Heart(game, 52, 577))
        gameObjects.add(game.getLink())
        gameObjects.add(Guard(game, 118, 971, Direction.UP))
        gameObjects.add(Guard(game, 885, 968, Direction.UP))
        gameObjects.add(Guard(game, 504, 564, Direction.DOWN))
        gameObjects.add(BlueSoldier(game, 331, 762, Direction.RIGHT, 50))
        gameObjects.add(BlueSoldier(game, 689, 762, Direction.LEFT, 50))
        gameObjects.add(WhiteSoldier(game, 1038, 681, Direction.LEFT))
        gameObjects.add(BlueSoldier(game, 883, 144, Direction.UP, 200))
        gameObjects.add(BlueSoldier(game, 116, 144, Direction.UP, 200))
        if (!game.song.equals("sounds/castle.mp3")) {
            game.stopMusic()
            game.playMusic("sounds/castle.mp3", true)
        }
        handleSwitchScene(entrance)
    }

    fun handleSwitchScene(exit: Rectangle) {
        if (exit === exitUp) {
            game.setScene(CastleBasementScene(game, "CastleScene"))
        }
        if (exit === exitDown) {
            game.setScene(HyruleScene(game, "CastleScene"))
        }
    }

    fun handleSwitchScene(entrance: String) {
        if (entrance == "HyruleScene") {
            moveScene(252, 607)
            game.getLink().setXHardCore(250)
            game.getLink().setYHardCore(326)
        }
        if (entrance == "CastleBasementScene") {
            moveScene(254, 1)
            game.getLink().setXHardCore(250)
            game.getLink().setYHardCore(119)
        }
    }
}