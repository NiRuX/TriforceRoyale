package zelda

import zelda.engine.Game
//import zelda.osDetection.getOperatingSystemType
import kotlin.jvm.JvmStatic

class Main : JFrame() {
    private val game: Game
    private val view: View
    private val ctl: Controller

    init {
        setIgnoreRepaint(true)
        game = Game()
        if (game.isDebug) {
            setLocationRelativeTo(null)
            setSize(575, 445)
            //Debug code will be updated in time.
        }
        setSize(515, 415) // Code is now functional
        setLocationRelativeTo(null) // Set to the center of the screen relative to x,y getSize.
        setTitle(" TriforceRoyale ")
        setUndecorated(true)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        setVisible(true)
        view = View(game, this)
        ctl = Controller(game, view, this)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // Start the osDetection Class function and return a value for the OS type.
            //getOperatingSystemType()
        }

        fun RunGame() {
            Main()
        }
    }
}