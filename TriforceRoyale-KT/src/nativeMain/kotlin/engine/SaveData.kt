package zelda.engine

import zelda.link.Link

/**
 *
 * @author maartenhus
 */
class SaveData(link: Link, scene: Scene) : java.io.Serializable {
    val health: Int
    val rupee: Int
    val sceneName: String

    init {
        health = link.getHealth()
        rupee = link.getRupee()
        sceneName = scene.getName()
    }
}