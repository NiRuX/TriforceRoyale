package zelda.enemy

import zelda.karacter.Direction

/**
 *
 * @author maartenhus
 */
class PatrolBehavior(private val soldier: BlueSoldier, private val max: Int) : Behavior() {
    private var ticks = 0
    private val step = 1
    private var eyeView: Polygon? = null
    protected var behavior: Behavior? = null

    init {
        move()
    }

    override fun behave() {
        soldier.getGame().getScene().removeEyeView(eyeView)
        when (soldier.getDirection()) {
            UP -> {
                val evxposup = intArrayOf(
                    soldier.getX(),
                    soldier.getX() - 30,
                    soldier.getX() - 20,
                    soldier.getX() + 35,
                    soldier.getX() + 45,
                    soldier.getX() + 15
                )
                val evyposup = intArrayOf(
                    soldier.getY(),
                    soldier.getY() - 40,
                    soldier.getY() - 50,
                    soldier.getY() - 50,
                    soldier.getY() - 40,
                    soldier.getY()
                )
                eyeView = Polygon(evxposup, evyposup, evxposup.size)
                soldier.getGame().getScene().addEyeView(eyeView)
            }
            DOWN -> {
                val evxposdown = intArrayOf(
                    soldier.getX(),
                    soldier.getX() - 30,
                    soldier.getX() - 20,
                    soldier.getX() + 35,
                    soldier.getX() + 45,
                    soldier.getX() + 15
                )
                val evyposdown = intArrayOf(
                    soldier.getY() + soldier.getHeight(),
                    soldier.getY() + soldier.getHeight() + 40,
                    soldier.getY() + soldier.getHeight() + 50,
                    soldier.getY() + soldier.getHeight() + 50,
                    soldier.getY() + soldier.getHeight() + 40,
                    soldier.getY() + soldier.getHeight()
                )
                eyeView = Polygon(evxposdown, evyposdown, evxposdown.size)
                soldier.getGame().getScene().addEyeView(eyeView)
            }
            LEFT -> {
                val evxposleft = intArrayOf(
                    soldier.getX(),
                    soldier.getX() - 40,
                    soldier.getX() - 50,
                    soldier.getX() - 50,
                    soldier.getX() - 40,
                    soldier.getX()
                )
                val evyposleft = intArrayOf(
                    soldier.getY() + 20,
                    soldier.getY() + 50,
                    soldier.getY() + 40,
                    soldier.getY() - 15,
                    soldier.getY() - 25,
                    soldier.getY() + 5
                )
                eyeView = Polygon(evxposleft, evyposleft, evxposleft.size)
                soldier.getGame().getScene().addEyeView(eyeView)
            }
            RIGHT -> {
                val evxposright = intArrayOf(
                    soldier.getX() + soldier.getWidth(),
                    soldier.getX() + soldier.getWidth() + 40,
                    soldier.getX() + soldier.getWidth() + 50,
                    soldier.getX() + soldier.getWidth() + 50,
                    soldier.getX() + soldier.getWidth() + 40,
                    soldier.getX() + soldier.getWidth()
                )
                val evyposright = intArrayOf(
                    soldier.getY() + 20,
                    soldier.getY() + 50,
                    soldier.getY() + 40,
                    soldier.getY() - 15,
                    soldier.getY() - 25,
                    soldier.getY() + 5
                )
                eyeView = Polygon(evxposright, evyposright, evxposright.size)
                soldier.getGame().getScene().addEyeView(eyeView)
            }
        }
        val area: java.awt.geom.Area = java.awt.geom.Area()
        area.add(java.awt.geom.Area(eyeView))
        area.intersect(java.awt.geom.Area(soldier.getGame().getLink().getRectangle()))
        if (!area.isEmpty()) {
            soldier.setBehavior(AttackBehavior(soldier))
        }
        if (soldier.getStateString().equals("WalkState")) {
            max += step
            if (max > max) {
                move()
                max = -1
            }
        }
    }

    private fun move() {
        when (soldier.getDirection()) {
            UP -> soldier.setDirection(Direction.DOWN)
            DOWN -> soldier.setDirection(Direction.UP)
            LEFT -> soldier.setDirection(Direction.RIGHT)
            RIGHT -> soldier.setDirection(Direction.LEFT)
        }
    }
}