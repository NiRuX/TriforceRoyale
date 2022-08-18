package zelda.engine

/**
 *
 * @author maartenhus
 */
class GObjectComparator : java.util.Comparator<Any?> {
    override fun compare(obj1: Any, obj2: Any): Int {
        val gobj1 = obj1 as GObject
        val gobj2 = obj2 as GObject
        if (gobj1.z > gobj2.z) {
            return 1
        }
        return if (gobj1.z < gobj2.z) {
            -1
        } else 0
    }
}