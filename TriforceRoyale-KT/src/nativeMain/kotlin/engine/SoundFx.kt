package zelda.engine

/**
 *
 * @author maartenhus
 */
class SoundFx(game: Game?, mp3: java.net.URL?) : Sound(game!!, mp3) {
    override fun run() {
        while (!player.isComplete()) // if song is not over
        {
            try {
                player.play()
                java.lang.Thread.sleep(1000)
            } catch (ee: java.lang.Exception) {
                ee.printStackTrace()
            }
        }
        player.close()
    }
}