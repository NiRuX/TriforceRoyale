package zelda.collision

/**
 * A list of weapons.
 * Hittable uses Weapon to show what weapon a GObject was hit by.
 * A GObject may react differently on Bombs and Sword hits.
 *
 * @author maartenhus
 */
enum class Weapon {
    ARROW, BOMB, SWORD
}