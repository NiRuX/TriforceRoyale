package zelda.engine

import zelda.items.Heart
import zelda.items.Rupee

/**
 * A GObject is something that gets drawn on the View, checks if it collides and animates itself.
 *
 * @author maartenhus
 */
abstract class GObject(
	var game: Game,
	protected var x: Int,
	protected var y: Int,
	var width: Int,
	var height: Int,
	image: String?
) :
	DrawAble {
	var isAlive = true
	var z = 0
		protected set
	var isCheckcollision = true // Should the objects check for collisions when x or y moves.
	var isLiquid = false // Can other GObjects move through the object.
	var isScreenAdjust = true // Does this object adjust its position when the screen moves.
	protected var sprite: Sprite
	var animation: Array<String>
	var animationCounter = 0
		protected set
	var animationInterval: Long = 90
	protected var lastAnimation: Long = java.lang.System.currentTimeMillis()

	init {
		sprite = Sprite(image)
	}

	/**
	 * What the GObject is supposed to do in the loop.
	 */
	fun doInLoop() {}

	/**
	 * What the GObject needs to do pre animation.
	 */
	protected fun preAnimation() {}

	/**
	 * What the GObject needs to do post animation.
	 */
	protected fun postAnimation() {}

	/**
	 * What the GObject does when it has a collision.
	 */
	protected fun collision(hitObject: GObject?) {}
	protected fun wallCollision() {}
	fun animate() {
		if (java.lang.System.currentTimeMillis() > lastAnimation + animationInterval) // if it time to reanimate
		{
			preAnimation()

			//System.out.println("Animation " + animationCounter + " == " + animation.length);
			if (animationCounter == animation.size) // if all animations are done.
			{
				//reset the counter.
				animationCounter = 0
			}
			try {
				// Set the next animation image of the GObject.
				sprite.setSprite(spriteLoc.get(animation[animationCounter]))
			} catch (e: java.lang.Exception) {
				//System.out.println("Animation " + animationCounter + " == " + animation.length);
				animationCounter = 0
			}
			animationCounter += 1
			lastAnimation = java.lang.System.currentTimeMillis()
			postAnimation()
		}
	}

	override fun draw(g: Graphics2D) {
		val img: Image = sprite.getImage()
		g.drawImage(img, x, y, sprite.getWidth(), sprite.getHeight(), null)
	}

	private fun isCollision(newX: Int, newY: Int): Boolean {
		val rect = Rectangle(newX, newY, width, height)
		var collision = false
		for (poly in game.getScene().getSolids())  //for each solid object
		{
			val area: java.awt.geom.Area = java.awt.geom.Area()
			area.add(java.awt.geom.Area(rect))
			area.intersect(java.awt.geom.Area(poly)) //check if there is a collision
			if (!area.isEmpty()) // if isEmpty is false there is a collision
			{
				collision = true
				wallCollision()
			}
		}
		for (obj in game.getScene().getGObjects()) {
			if (obj.isCheckcollision) {
				val area: java.awt.geom.Area = java.awt.geom.Area()
				area.add(java.awt.geom.Area(rect))
				area.intersect(java.awt.geom.Area(obj.rectangle))
				if (!area.isEmpty() && this !== obj) // if area is empty, and the obj is not isself. (Self-collision)
				{
					collision(obj) //report collision to self, with the object that hit it.
					obj.collision(this) //report collision to object that got hit with itself.
					if (!obj.isLiquid) {
						collision = true
					}
				}
			}
		}
		return collision
	}

	fun getX(): Int {
		return x
	}

	fun setX(newX: Int) {
		if (!isCheckcollision || !isCollision(newX, y)) {
			x = newX
		}
	}

	fun getY(): Int {
		return y
	}

	fun setY(newY: Int) {
		if (!isCheckcollision || !isCollision(x, newY)) {
			y = newY
		}
	}

	fun setYHardCore(y: Int) {
		this.y = y
	}

	fun setXHardCore(x: Int) {
		this.x = x
	}

	val rectangle: Rectangle
		get() = Rectangle(x, y, width, height)

	fun resetAnimationCounter() {
		animationCounter = 0
	}

	fun randomGoodie() {
		val r: Int = (java.lang.Math.random() * 200).toInt()
		//System.out.println(r);
		if (r < 50) {
			if (r < 25) {
				game.getScene().addNewGObject(Heart(game, x, y))
			} else {
				game.getScene().addNewGObject(Rupee(game, x, y))
			}
		}
	}

	companion object {
		protected var spriteLoc: java.util.HashMap<String, Rectangle> = java.util.HashMap<String, Rectangle>()
	}
}