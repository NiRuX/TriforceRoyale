package zelda.scene

import zelda.engine.Game
import zelda.engine.Scene
import zelda.items.GuiHeart
import zelda.items.GuiRupee

/**
 * A specialised Scene object for the Zelda game.
 *
 * @author maartenhus
 */
abstract class ZeldaScene(game: Game, img: String?, sceneName: String?) :
    Scene(game, img, sceneName!!) {
    protected var exits: java.util.ArrayList<Rectangle> = java.util.ArrayList<Rectangle>()
    private var adjust = false
    private val XSen //left/right sensitivity for when the scene adapts too link
            : Int
    private val YSen //up/down sensitivity for when the scene adapts too link
            : Int

    init {
        XSen = game.width / 2
        YSen = game.height / 2
        sprite.setSprite(Rectangle(0, 0, game.width, game.height))
        GuiHeart.clear()
        var heart: GuiHeart
        for (i in 0..4)  //draw live bar hearts
        {
            heart = GuiHeart(game, game.width - 130 + i * 12, 50)
            gameObjects.add(heart)
        }
        val rupee = GuiRupee(game, 100, game.height / 11) // draw rupee amount
        gameObjects.add(rupee)
    }

    override fun handleInput() {
        super.handleInput()
        checkLinkIsInExit()
        if (game.getLink().moveinput()) {
            adjust = true
        }
        if (adjust) {
            if (!game.getLink().stateString.equals("SwordState") && !game.getLink().stateString.equals("BowState")) //ignore swordstate and bowstate
            {
                adjustScene(game.getLink().getX(), game.getLink().getY())
            }
        }
        inputHook()

        //System.out.println("Scene is at:");
        //System.out.println(sprite.getX() + ", " + sprite.getY());
    }

    open fun inputHook() {}
    private fun checkLinkIsInExit() {
        for (exit in exits) {
            if (exit.intersects(game.getLink().rectangle)) {
                handleSwitchScene(exit)
            }
        }
    }

    fun adjustScene(moveToX: Int, moveToY: Int) {
        if (moveToX > sprite.width - XSen) // link moves too far to the right.
        {
            val newX = sprite.x + MOD
            // System.out.println((newX + sprite.getWidth()) + " <= " + sprite.getImageWidth());
            if (newX + sprite.width <= sprite.imageWidth) {
                //System.out.println(newX + " " + sprite.getX());
                game.getLink().setX(game.getLink().getX() - MOD)
                modShapes(-MOD, 0)
                sprite.x = newX
            }
        }
        if (moveToX < XSen) // link moves too far to the left
        {
            val newX = sprite.x - MOD
            if (newX > 0) {
                game.getLink().setX(game.getLink().getX() + MOD)
                modShapes(MOD, 0)
                sprite.x = newX
            }
        }
        if (moveToY > sprite.height - YSen) // link moves too far down
        {
            val newY = sprite.y + MOD
            if (newY + sprite.height <= sprite.imageHeight) {
                game.getLink().setY(game.getLink().getY() - MOD)
                modShapes(0, -MOD)
                sprite.y = newY
            }
        }
        if (moveToY < YSen) // link moves to far up
        {
            val newY = sprite.y - MOD
            if (newY > 0) {
                game.getLink().setY(game.getLink().getY() + MOD)
                modShapes(0, MOD)
                sprite.y = newY
            }
        }
    }

    /**
     * When the screen moves everything else should move in the opposite direction.
     * otherwise they won't sit still.
     *
     * @param modX
     * @param modY
     */
    override fun modShapes(modX: Int, modY: Int) {
        for (poly in solids) {
            poly.translate(modX, modY)
        }
        for (rect in exits) {
            rect.translate(modX, modY)
        }
        for (obj in gameObjects) {
            if (obj.isScreenAdjust) // should it adjust when screen moves.
            {
                obj.setXHardCore(obj.getX() + modX)
                obj.setYHardCore(obj.getY() + modY)
            }
        }
    }

    override fun draw(g2: Graphics2D) {
        g2.drawImage(sprite.getImage(), 0, 0, game.width, game.height, null)
        g2.setColor(java.awt.Color.white)
        val f = Font("Serif", Font.BOLD, 12)
        g2.setFont(f)
        g2.drawString("-- LIFE --", game.width - 122, game.height / 9)
        g2.drawString("" + game.getLink().rupee, 102, game.height / 7)
    }

    fun getExits(): java.util.ArrayList<Rectangle> {
        return exits
    }

    abstract fun handleSwitchScene(exit: Rectangle?)
    abstract fun handleSwitchScene(entrance: String?)
}