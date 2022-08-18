package zelda

import zelda.engine.Game

/**
 * This class is a 'development' only class. It lets you get coordinates on the 'screen'
 * these can be used to populate the solid objects in the Scene's.
 *
 * @author maartenhus
 */
class PolyCreator(game: Game) : MouseAdapter() {
	private val game: Game
	private val poly: Polygon = Polygon()

	init {
		this.game = game
	}

	override fun mouseClicked(e: java.awt.event.MouseEvent) {
		if (e.isControlDown()) // print the coordinates in the console in int[] form.
		{
			print("int[] xpos = {")
			for (i in poly.xpoints.indices) {
				print(poly.xpoints.get(i).toString() + ", ")
			}
			println("};")
			print("int[] ypos = {")
			for (i in poly.ypoints.indices) {
				print(poly.ypoints.get(i).toString() + ", ")
			}
			println("};")
		} else  // add point in the array.
		{
			val x: Int = game.getScene().getSprite().getX() + e.getX()
			val y: Int = game.getScene().getSprite().getY() + e.getY()
			if (x != 0 && y != 0) {
				poly.addPoint(x, y)
				//System.out.println("Relative:" + x + " " + y);
				//System.out.println("Real:" + e.getX() + " " + e.getY());
			}
		}
	}
}