package zelda.link

import zelda.enemy.Soldier
import zelda.enemy.armos.ArmosKnight
import zelda.engine.GObject
import zelda.engine.Game
import zelda.items.Bomb
import zelda.items.Heart
import zelda.items.Rupee
import zelda.karacter.Direction
import zelda.karacter.Karacter

/**
 * The players avatar in the game.
 *
 * @author maartenhus
 */
class Link(game: Game?, x: Int, y: Int) :
    Karacter(game, x, y, 17, 20, Direction.DOWN, "images/link.png") {
    private val inputInterval: Long = 50
    private var lastInput: Long = java.lang.System.currentTimeMillis()
    private var lastHit: Long = java.lang.System.currentTimeMillis()
    private val bomInterval: Long = 3000
    private var lastBom: Long = java.lang.System.currentTimeMillis()
    private val arrowInterval: Long = 1000
    private var lastArrow: Long = java.lang.System.currentTimeMillis()
    var rupee = 0

    init {
        spriteLoc.put("Link walk down 1", Rectangle(0, 0, 16, 23))
        spriteLoc.put("Link walk down 2", Rectangle(25, 0, 16, 23))
        spriteLoc.put("Link walk down 3", Rectangle(50, 0, 16, 23))
        spriteLoc.put("Link stand down", Rectangle(75, 0, 16, 23))
        spriteLoc.put("Link walk down 4", Rectangle(100, 0, 16, 23))
        spriteLoc.put("Link walk down 5", Rectangle(125, 0, 16, 23))
        spriteLoc.put("Link walk down 6", Rectangle(150, 0, 16, 23))
        spriteLoc.put("Link walk up 1", Rectangle(0, 25, 17, 23))
        spriteLoc.put("Link walk up 2", Rectangle(25, 25, 17, 23))
        spriteLoc.put("Link walk up 3", Rectangle(50, 25, 17, 23))
        spriteLoc.put("Link stand up", Rectangle(75, 25, 17, 23))
        spriteLoc.put("Link walk up 4", Rectangle(100, 25, 17, 23))
        spriteLoc.put("Link walk up 5", Rectangle(125, 25, 17, 23))
        spriteLoc.put("Link walk up 6", Rectangle(150, 25, 17, 23))
        spriteLoc.put("Link walk left 1", Rectangle(0, 50, 19, 24))
        spriteLoc.put("Link walk left 2", Rectangle(25, 50, 19, 24))
        spriteLoc.put("Link walk left 3", Rectangle(50, 50, 19, 24))
        spriteLoc.put("Link stand left", Rectangle(75, 50, 19, 24))
        spriteLoc.put("Link walk left 4", Rectangle(100, 50, 19, 24))
        spriteLoc.put("Link walk left 5", Rectangle(125, 50, 19, 24))
        spriteLoc.put("Link walk left 6", Rectangle(150, 50, 19, 24))
        spriteLoc.put("Link walk right 1", Rectangle(0, 75, 19, 24))
        spriteLoc.put("Link walk right 2", Rectangle(25, 75, 19, 24))
        spriteLoc.put("Link walk right 3", Rectangle(50, 75, 19, 24))
        spriteLoc.put("Link stand right", Rectangle(75, 75, 19, 24))
        spriteLoc.put("Link walk right 4", Rectangle(100, 75, 19, 24))
        spriteLoc.put("Link walk right 5", Rectangle(125, 75, 19, 24))
        spriteLoc.put("Link walk right 6", Rectangle(150, 75, 19, 24))
        spriteLoc.put("Link sword down 1", Rectangle(0, 100, 19, 24))
        spriteLoc.put("Link sword down 2", Rectangle(25, 100, 20, 30))
        spriteLoc.put("Link sword down 3", Rectangle(50, 100, 19, 32))
        spriteLoc.put("Link sword down 4", Rectangle(75, 100, 19, 32))
        spriteLoc.put("Link sword down 5", Rectangle(100, 100, 29, 30))
        spriteLoc.put("Link sword down 6", Rectangle(130, 100, 33, 28))
        spriteLoc.put("Link sword up 1", Rectangle(0, 150, 24, 23))
        spriteLoc.put("Link sword up 2", Rectangle(25, 150, 23, 23))
        spriteLoc.put("Link sword up 3", Rectangle(50, 150, 24, 25))
        spriteLoc.put("Link sword up 4", Rectangle(75, 150, 22, 31))
        spriteLoc.put("Link sword up 5", Rectangle(100, 150, 20, 33))
        spriteLoc.put("Link sword up 6", Rectangle(125, 150, 20, 35))
        spriteLoc.put("Link sword up 7", Rectangle(150, 150, 24, 30))
        spriteLoc.put("Link sword up 8", Rectangle(32, 174, 30, 26))
        spriteLoc.put("Link sword up 9", Rectangle(0, 175, 32, 23))
        spriteLoc.put("Link sword right 1", Rectangle(0, 200, 16, 23))
        spriteLoc.put("Link sword right 2", Rectangle(25, 200, 19, 23))
        spriteLoc.put("Link sword right 3", Rectangle(50, 200, 24, 24))
        spriteLoc.put("Link sword right 4", Rectangle(75, 200, 26, 24))
        spriteLoc.put("Link sword right 5", Rectangle(102, 200, 30, 22))
        spriteLoc.put("Link sword right 6", Rectangle(132, 200, 32, 25))
        spriteLoc.put("Link sword right 7", Rectangle(0, 225, 29, 23))
        spriteLoc.put("Link sword right 8", Rectangle(29, 225, 29, 29))
        spriteLoc.put("Link sword right 9", Rectangle(74, 225, 24, 30))
        spriteLoc.put("Link sword left 1", Rectangle(100, 225, 16, 23))
        spriteLoc.put("Link sword left 2", Rectangle(125, 225, 18, 23))
        spriteLoc.put("Link sword left 3", Rectangle(150, 225, 23, 24))
        spriteLoc.put("Link sword left 4", Rectangle(0, 250, 25, 25))
        spriteLoc.put("Link sword left 5", Rectangle(25, 254, 29, 21))
        spriteLoc.put("Link sword left 6", Rectangle(56, 254, 31, 21))
        spriteLoc.put("Link sword left 7", Rectangle(94, 254, 28, 21))
        spriteLoc.put("Link sword left 8", Rectangle(125, 253, 28, 28))
        spriteLoc.put("Link sword left 9", Rectangle(153, 250, 22, 31))
        spriteLoc.put("Link bow down 1", Rectangle(0, 300, 17, 25))
        spriteLoc.put("Link bow down 2", Rectangle(25, 300, 18, 21))
        spriteLoc.put("Link bow down 3", Rectangle(50, 300, 18, 22))
        spriteLoc.put("Link bow left 1", Rectangle(0, 325, 17, 22))
        spriteLoc.put("Link bow left 2", Rectangle(25, 325, 19, 21))
        spriteLoc.put("Link bow left 3", Rectangle(50, 325, 20, 20))
        spriteLoc.put("Link bow right 1", Rectangle(0, 350, 17, 23))
        spriteLoc.put("Link bow right 2", Rectangle(25, 350, 22, 23))
        spriteLoc.put("Link bow up 1", Rectangle(0, 375, 18, 22))
        spriteLoc.put("Link bow up 2", Rectangle(25, 375, 21, 21))
        spriteLoc.put("Link bow up 3", Rectangle(50, 375, 21, 22))
        spriteLoc.put("Link hit right", Rectangle(0, 425, 17, 21))
        spriteLoc.put("Link death right", Rectangle(50, 425, 27, 15))
        spriteLoc.put("Link death right 2", Rectangle(25, 425, 27, 19))
        spriteLoc.put("Link hit left", Rectangle(0, 450, 17, 21))
        spriteLoc.put("Link death left", Rectangle(50, 450, 24, 15))
        spriteLoc.put("Link death left 2", Rectangle(25, 450, 23, 19))
        sprite.setSprite(spriteLoc.get("Link stand down"))
        z = 2
        health = 5
        screenAdjust = false
        state = StandState(this)
    }

    fun dropBomb() {
        if (java.lang.System.currentTimeMillis() > lastBom + bomInterval) {
            when (direction) {
                Direction.UP -> game.getScene()!!.addNewGObject(Bomb(game, x + 2, y - 16))
                Direction.DOWN -> game.getScene()!!.addNewGObject(Bomb(game, x + 2, y + height))
                Direction.LEFT -> game.getScene()!!.addNewGObject(Bomb(game, x - 13, y + 7))
                Direction.RIGHT -> game.getScene()!!.addNewGObject(Bomb(game, x + width, y + 7))
            }
            lastBom = java.lang.System.currentTimeMillis()
        }
    }

    fun shootArrow() {
        if (java.lang.System.currentTimeMillis() > lastArrow + arrowInterval) {
            setState(BowState(this))
            lastArrow = java.lang.System.currentTimeMillis()
        }
    }

    fun handleInput() {
        //System.out.println("Location: " + x + ", " + y);
        if (java.lang.System.currentTimeMillis() > lastInput + inputInterval) {
            state!!.handleInput()
            lastInput = java.lang.System.currentTimeMillis()
        }
    }

    public override fun preAnimation() {
        state!!.handleAnimation()
    }

    override fun collision(hitObject: GObject?) {
        if (health == 0) {
            if (!stateString.equals("DeathState")) {
                //sgame.stopMusic();
                game.playFx("sounds/killed.mp3")
                setState(DeathState(this, direction))
                //alive = false;
            }
            if (health > 5) {
                game.stopMusic()
            }
        }
        if (hitObject is Soldier) {
            if (health > 0 && java.lang.System.currentTimeMillis() > lastHit + 800) {
                game.playFx("sounds/linkHurt.mp3")
                health--
                lastHit = java.lang.System.currentTimeMillis()

                //System.out.println("leven: " + health);
                val soldier: Soldier? = hitObject as Soldier?
                setState(TransState(this, soldier.getDirection()))
            }
        }
        if (hitObject is ArmosKnight) {
            if (health > 0 && java.lang.System.currentTimeMillis() > lastHit + 800) {
                game.playFx("sounds/linkHurt.mp3")
                health--
                lastHit = java.lang.System.currentTimeMillis()

                //System.out.println("leven: " + health);
                val armosKnight: ArmosKnight? = hitObject as ArmosKnight?
                setState(TransState(this, armosKnight.getDirection()))
            }
        }
        if (hitObject is Heart) {
            if (health < 5) {
                game.playFx("sounds/getItem.mp3")
                health++
            }
        }
        if (hitObject is Rupee) {
            game.playFx("sounds/getItem.mp3")
            rupee += 5
        }
    }

    //Handy dandy stuff that handles input
    fun moveinput(): Boolean {
        return game.isaPressed() || game.isdPressed() || game.iswPressed() || game.issPressed()
    }

    fun noMoveinput(): Boolean {
        return !game.isaPressed() && !game.isdPressed() && !game.iswPressed() && !game.issPressed()
    }
}