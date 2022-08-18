package zelda.engine

import kotlin.jvm.Synchronized

/**
 * This represents a level in the game.
 *
 * @author maartenhus
 */
abstract class Scene(protected var game: Game, img: String?, sceneName: String) : DrawAble {
    protected var sprite: Sprite
    protected var newGameObjects: java.util.ArrayList<GObject> = java.util.ArrayList<GObject>()
    protected var gameObjects: java.util.ArrayList<GObject> = java.util.ArrayList<GObject>()
    protected var solids: java.util.ArrayList<Polygon> = java.util.ArrayList<Polygon>()
    protected var hitters: java.util.ArrayList<Rectangle> = java.util.ArrayList<Rectangle>()
    protected var eyeViews: java.util.ArrayList<Polygon> = java.util.ArrayList<Polygon>()
    var name: String
        protected set

    init {
        sprite = Sprite(img)
        name = sceneName
    }

    override fun draw(g2: Graphics2D) {
        g2.drawImage(sprite.getImage(), 0, 0, game.width, game.height, null)
    }

    @Synchronized
    fun handleInput() {
        for (obj in newGameObjects) {
            gameObjects.add(obj)
        }
        java.util.Collections.sort(gameObjects, GObjectComparator())
        val it: MutableIterator<GObject> = gameObjects.iterator()
        while (it.hasNext()) {
            val obj = it.next()
            if (!obj.isAlive) {
                it.remove()
            }
        }
        newGameObjects.clear()
    }

    fun moveScene(toX: Int, toY: Int) {
        var moved = false
        do {
            moved = false
            if (sprite.getX() < toX) {
                val newX: Int = sprite.getX() + MOD
                if (newX + sprite.getWidth() <= sprite.getImageWidth()) {
                    game.getLink().setX(game.getLink().getX() - MOD)
                    modShapes(-MOD, 0)
                    sprite.setX(newX)
                    moved = true
                }
            }
            if (sprite.getX() > toX) // link moves too far to the left
            {
                val newX: Int = sprite.getX() - MOD
                if (newX > 0) {
                    game.getLink().setX(game.getLink().getX() + MOD)
                    modShapes(MOD, 0)
                    sprite.setX(newX)
                    moved = true
                }
            }
            if (sprite.getY() < toY) {
                val newY: Int = sprite.getY() + MOD
                if (newY + sprite.getHeight() <= sprite.getImageHeight()) {
                    game.getLink().setY(game.getLink().getY() - MOD)
                    modShapes(0, -MOD)
                    sprite.setY(newY)
                    moved = true
                }
            }
            if (sprite.getY() > toY) {
                val newY: Int = sprite.getY() - MOD
                if (newY > 0) {
                    game.getLink().setY(game.getLink().getY() + MOD)
                    modShapes(0, MOD)
                    sprite.setY(newY)
                    moved = true
                }
            }
        } while (moved)
    }

    /**
     * When the screen moves everything else should move in the opposite direction.
     * otherwise they won't sit still.
     *
     * @param modX
     * @param modY
     */
    fun modShapes(modX: Int, modY: Int) {
        for (poly in solids) {
            poly.translate(modX, modY)
        }
        for (obj in gameObjects) {
            if (obj.isScreenAdjust) // should it adjust when screen moves.
            {
                obj.setXHardCore(obj.getX() + modX)
                obj.setYHardCore(obj.getY() + modY)
            }
        }
    }

    fun addGObject(gObject: GObject?) {
        gameObjects.add(gObject)
    }

    fun addNewGObject(gObject: GObject?) {
        newGameObjects.add(gObject)
    }

    fun getSolids(): java.util.ArrayList<Polygon> {
        return solids
    }

    val gObjects: java.util.ArrayList<GObject>
        get() = gameObjects

    fun addHitter(rect: Rectangle?) {
        hitters.add(rect)
    }

    fun removeHitter(rect: Rectangle?) {
        hitters.remove(rect)
    }

    fun getHitters(): java.util.ArrayList<Rectangle> {
        return hitters
    }

    fun addEyeView(poly: Polygon?) {
        eyeViews.add(poly)
    }

    fun removeEyeView(poly: Polygon?) {
        eyeViews.remove(poly)
    }

    fun getEyeViews(): java.util.ArrayList<Polygon> {
        return eyeViews
    }

    //remove after done with PolyCreator
    fun getSprite(): Sprite {
        return sprite
    }

    companion object {
        protected const val MOD = 1
    }
}