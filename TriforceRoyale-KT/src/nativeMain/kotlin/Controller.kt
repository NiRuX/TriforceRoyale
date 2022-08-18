package zelda

import zelda.engine.Game

/**
 * The Controller is reponsible for the gameloop.
 * And it handles user keyinput for the game.
 *
 * @author maartenhus
 */
class Controller(game: Game, view: View, frame: JFrame) : Runnable, KeyListener {
    private val thread: java.lang.Thread
    private val game: Game
    private val view: View
    private val polyCreator: PolyCreator? = null

    init {
        this.game = game
        this.view = view
        frame.addMouseListener(PolyCreator(game))
        frame.addKeyListener(this)
        thread = java.lang.Thread(this, "GameLoop")
        thread.start()
    }

    /**
     * This function represents the gameloop, it does stuff like make objects
     * react on input and draw the game.
     */
    override fun run() {
        while (game.isRunning()) {
            try {
                if (!game.isPaused()) {
                    game.getScene().handleInput() // let scene handle user input for menu's etc.
                    game.getLink().handleInput() // let link handle key input.
                    for (obj in game.getScene().getGObjects()) {
                        obj.doInLoop() // this lets the GObject hook in on the gameloop
                    }
                }
                try {
                    view.draw()
                } catch (e: java.lang.Exception) {
                }
                java.lang.Thread.sleep(game.getGameSpeed())
            } catch (e: InterruptedException) {
            }
        }

        // if the game is not running close up.
        view.exitFullScreen()
        game.quit()
    }

    override fun keyPressed(e: KeyEvent) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            game.setRunning(false) //quit game
        }
        if (e.getKeyCode() == KeyEvent.VK_P) {
            game.setPaused(!game.isPaused()) //pauze game
        }
        when (e.getKeyCode()) {
            KeyEvent.VK_A -> game.setaPressed(true)
            KeyEvent.VK_D -> game.setdPressed(true)
            KeyEvent.VK_W -> game.setwPressed(true)
            KeyEvent.VK_S -> game.setsPressed(true)
            KeyEvent.VK_J -> game.setjPressed(true)
            KeyEvent.VK_K -> game.setkPressed(true)
            KeyEvent.VK_L -> game.setlPressed(true)
            KeyEvent.VK_ENTER -> game.setEnterPressed(true)
        }
    }

    override fun keyReleased(e: KeyEvent) {
        when (e.getKeyCode()) {
            KeyEvent.VK_A -> game.setaPressed(false)
            KeyEvent.VK_D -> game.setdPressed(false)
            KeyEvent.VK_W -> game.setwPressed(false)
            KeyEvent.VK_S -> game.setsPressed(false)
            KeyEvent.VK_J -> game.setjPressed(false)
            KeyEvent.VK_K -> game.setkPressed(false)
            KeyEvent.VK_L -> game.setlPressed(false)
            KeyEvent.VK_ENTER -> game.setEnterPressed(false)
        }
    }

    override fun keyTyped(e: KeyEvent) {}
}