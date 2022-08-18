package zelda.engine

import zelda.Main
import kotlin.jvm.Synchronized

/**
 * Represents a spritesheet.
 *
 * @author maartenhus
 */
class Sprite(img: String?) {
    private var image: BufferedImage? = null
    var x = 0
    var y = 0
    var width = 0
    var height = 0

    init {
        val imageUrl: java.net.URL = Main::class.java.getResource(img)
        try {
            assert(imageUrl != null)
            image = ImageIO.read(imageUrl)
        } catch (ignored: IOException) {
        }
    }

    fun setSprite(rect: Rectangle) {
        x = rect.getX().toInt()
        y = rect.getY().toInt()
        width = rect.getWidth().toInt()
        height = rect.getHeight().toInt()
    }

    @Synchronized
    fun getImage(): BufferedImage {
        return image.getSubimage(x, y, width, height)
    }

    val imageWidth: Int
        get() = image.getWidth()
    val imageHeight: Int
        get() = image.getHeight()
}