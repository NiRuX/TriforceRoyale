package zelda.engine

import javazoom.jl.player.Player

/**
 *
 * @author maartenhus
 */
abstract class Sound(protected var game: Game, mp3: java.net.URL) : Runnable {
    protected var player: Player? = null
    protected var th: java.lang.Thread
    protected var mp3: java.net.URL

    init {
        this.mp3 = mp3
        th = java.lang.Thread(this, mp3.getFile())
    }

    fun play() {
        // Don't play the same Music or SoundFx right after eachother.
        // For example see bushCut.mp3 if multible bushes are cut at the same time just play it once.
        // Hopefully this will fix the "cant rip 0x00 bug".
        if (java.lang.System.currentTimeMillis() > lastPlayed + playInterval || lastSong != mp3.getFile()) {
            //System.out.println(mp3.getFile());
            try {
                player = Player(mp3.openStream())
                th.start()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            lastSong = mp3.getFile()
            lastPlayed = java.lang.System.currentTimeMillis()
        }
    }

    abstract fun run()

    companion object {
        private var lastPlayed: Long = java.lang.System.currentTimeMillis()
        private const val playInterval: Long = 1000
        private var lastSong = ""
    }
}