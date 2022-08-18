package zelda

import zelda.engine.Game
import zelda.scene.ZeldaScene

/**
 * This class handles all the drawing.
 *
 * @author maartenhus
 */
class View(game: Game, frame: JFrame) {
    private val game: Game
    private val buffer: BufferStrategy
    private val bi: BufferedImage
    private val gd: GraphicsDevice
    private val displayWidth = 640
    private val displayHeight = 480
    private var x = 0
    private var y = 0

    init {
        this.game = game
        val ge: GraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment()
        gd = ge.getDefaultScreenDevice()
        val gc: GraphicsConfiguration = gd.getDefaultConfiguration()
        if (!game.isDebug()) {
            //Replaced setFullScreenWindow(true) to (null) to cancel out the fullscreen function.
            gd.setFullScreenWindow(null) //needs to be done before call too isDisplayChangeSupported
        }
        if (gd.isDisplayChangeSupported() && !game.isDebug()) {
            gd.setDisplayMode(DisplayMode(displayWidth, displayHeight, 32, DisplayMode.REFRESH_RATE_UNKNOWN))
        }
        frame.createBufferStrategy(2)
        frame.setBackground(java.awt.Color.BLACK)
        buffer = frame.getBufferStrategy()
        bi = gc.createCompatibleImage(game.getWidth(), game.getHeight())
        if (!game.isDebug()) {
            x = (displayWidth - game.getWidth()) / 18
            y = (displayHeight - game.getHeight()) / 11
        }
    }

    fun draw() {
        val graphics: Graphics = buffer.getDrawGraphics()
        val g2: Graphics2D = bi.createGraphics()

        //for background in fullscreen.
        g2.setColor(java.awt.Color.black)

        //System.out.println("draw");
        game.getScene().draw(g2)
        g2.setColor(java.awt.Color.red)

        //animate, and draw every GObject from Scene
        for (obj in game.getScene().getGObjects()) {
            if (game.isDebug()) g2.draw(obj.getRectangle())
            if (!game.isPaused()) {
                obj.animate()
            } else {
                g2.setColor(java.awt.Color.white)
                g2.drawString("-- Pauzed --", game.getWidth() / 2 - 30, game.getHeight() / 2)
                g2.setColor(java.awt.Color.red)
            }
            obj.draw(g2)
        }
        if (game.isDebug()) {
            //Draw solids on the map
            for (s in game.getScene().getSolids()) {
                g2.draw(s)
            }

            //draw blue box when link strikes debug
            for (r in game.getScene().getHitters()) {
                g2.setColor(java.awt.Color.blue)
                g2.draw(r)
            }

            //draw green box for eye views
            for (v in game.getScene().getEyeViews()) {
                g2.setColor(java.awt.Color.green)
                g2.draw(v)
            }
            if (game.getScene() is ZeldaScene) {
                val zeldaScene: ZeldaScene = game.getScene() as ZeldaScene
                for (v in zeldaScene.getExits()) {
                    g2.setColor(java.awt.Color.magenta)
                    g2.draw(v)
                }
            }
        }
        if (!game.isDebug()) {
            graphics.drawImage(bi, x, y, null)
        } else {
            graphics.drawImage(bi, 0, 0, null)
        }
        if (!buffer.contentsLost()) buffer.show()
        graphics.dispose()
        g2.dispose()
    }

    fun exitFullScreen() {
        gd.setFullScreenWindow(null)
    }
}