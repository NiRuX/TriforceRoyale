package zelda.engine

/**
 * This class plays Music.
 *
 * @author maartenhus
 */
class Music(game: Game?, mp3: java.net.URL?, songname: String, private val loop: Boolean) :
    Sound(game, mp3) {
    var song = ""

    init {
        song = songname
    }

    fun run() {
        while (!player.isComplete()) // if song is not over
        {
            try {
                player.play()
                java.lang.Thread.sleep(1000)
            } catch (ee: java.lang.Exception) {
                ee.printStackTrace()
            }
        }
        if (loop) //if song is over but its on a loop replay the song.
        {
            game.playMusic(song, true)
        }
        player.close()
    }

    fun stop() {
        player.close()
    }
}