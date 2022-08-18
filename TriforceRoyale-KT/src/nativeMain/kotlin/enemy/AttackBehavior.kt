package zelda.enemy

import zelda.karacter.Direction
import zelda.karacter.Karacter
import zelda.link.Link

/**
 *
 * @author vincentklarholz
 */
class AttackBehavior(private val soldier: Karacter) : Behavior() {
    private val link: Link
    private var valueX = 0
    private var valueY = 0

    init {
        //System.out.println("here");
        link = soldier.game.getLink()
    }

    fun behave() {
        valueX = java.lang.Math.abs(link.getX() - soldier.getX())
        valueY = java.lang.Math.abs(link.getY() - soldier.getY())

        //check which direct between link and soldier is longer, X or Y
        if (valueX < valueY) {
            //Set new direction for soldier
            //Soldier up
            if (link.getY() < soldier.getY()) {
                soldier.setY(soldier.getY() - 1)
                if (soldier.direction !== Direction.UP) {
                    soldier.direction = Direction.UP
                }
            } else if (link.getY() > soldier.getY()) {
                soldier.setY(soldier.getY() + 1)
                if (soldier.direction !== Direction.DOWN) {
                    soldier.direction = Direction.DOWN
                }
            }
        } else {
            //Set new direction for soldier
            //Soldier left
            if (link.getX() < soldier.getX()) {
                soldier.setX(soldier.getX() - 1)
                if (soldier.direction !== Direction.LEFT) {
                    soldier.direction = Direction.LEFT
                }
            } else if (link.getX() > soldier.getX()) {
                soldier.setX(soldier.getX() + 1)
                if (soldier.direction !== Direction.RIGHT) {
                    soldier.direction = Direction.RIGHT
                }
            }
        }

        //Set new X for soldier
        //Soldier left
        if (link.getX() < soldier.getX()) {
            soldier.setX(soldier.getX() - 1)
        } else if (link.getX() > soldier.getX()) {
            soldier.setX(soldier.getX() + 1)
        }

        //Set new Y for soldier
        //Soldier up
        if (link.getY() < soldier.getY()) {
            soldier.setY(soldier.getY() - 1)
        } else if (link.getY() > soldier.getY()) {
            soldier.setY(soldier.getY() + 1)
        }
    }
}