package zelda.collision

/**
 * Can something can get hit by a weapon?
 * Weapon represents the weapon that was used.
 *
 * @author maartenhus
 */
interface Hittable {
    fun hitBy(weapon: Weapon?)
}