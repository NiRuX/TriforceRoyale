package zelda.engine

import zelda.Main
import zelda.link.Link
import zelda.menu.MainMenu
import zelda.scene.ArmosScene
import zelda.scene.BattleScene
import zelda.scene.CastleBasementScene
import zelda.scene.CastleScene
import zelda.scene.DungeonScene
import zelda.scene.ForrestScene
import zelda.scene.HiddenScene
import zelda.scene.HouseScene
import zelda.scene.HyruleScene
import kotlin.jvm.Synchronized

/**
 * This class represents the Game: Legend of Zelda: a Link to the Past!
 *
 * @author maartenhus
 */
class Game {
    var isRunning = true
    var isPaused = false
    val isDebug = false
    var gameSpeed = 2
    val width = 500
    val height = 400
    private val link: Link
    private var scene: Scene?
    private var music: Music? = null
    private var fx: SoundFx? = null
    private var aPressed = false
    private var sPressed = false
    private var dPressed = false
    private var wPressed = false
    private var jPressed = false
    private var kPressed = false
    private var lPressed = false
    var isEnterPressed = false
    private val lastHit: Long = java.lang.System.currentTimeMillis()
    private val lastHit2: Long = java.lang.System.currentTimeMillis()

    init {
        link = Link(this, 100, 100)
        scene = MainMenu(this)
    }

    fun quit() {
        if (music != null) music.stop()
        save()
        try {
            java.lang.Thread.sleep(1000) // give it some time to shutdown the music nicely.
        } catch (ex: InterruptedException) {
        }
        java.lang.System.exit(0)
    }

    /**
     * Make the game play music.
     *
     * @param mp3file
     * @param loop
     */
    fun playMusic(mp3file: String?, loop: Boolean) {
        val mp3: java.net.URL = Main::class.java.getResource(mp3file)
        music = Music(this, mp3, mp3file, loop)
        music.play()
    }

    fun stopMusic() {
        music.stop()
    }

    val song: String
        get() = if (music == null) {
            ""
        } else music.getSong()

    fun playFx(mp3file: String?) {
        val mp3: java.net.URL = Main::class.java.getResource(mp3file)
        fx = SoundFx(this, mp3)
        fx.play()
    }

    fun load() {
        var fis: FileInputStream? = null
        var `in`: ObjectInputStream? = null
        try {
            fis = FileInputStream("Zelda.ser")
            `in` = ObjectInputStream(fis)
            val data: SaveData = `in`.readObject() as SaveData
            val scn: Scene? = initScene(data.getSceneName())
            setScene(scn)
            link.setHealth(data.getHealth())
            link.setRupee(data.getRupee())
            `in`.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        } catch (ex: ClassNotFoundException) {
            ex.printStackTrace()
        }
    }

    fun initScene(sceneName: String): Scene? {
        var scn: Scene? = null
        if (sceneName == "HouseScene") {
            scn = HouseScene(this, "GameStart")
        }
        if (sceneName == "HyruleScene") {
            scn = HyruleScene(this, "HouseScene")
        }
        if (sceneName == "HiddenScene") {
            scn = HiddenScene(this, "HyruleSceneHatch")
        }
        if (sceneName == "ForrestScene") {
            scn = ForrestScene(this, "HouseScene")
        }
        if (sceneName == "DungeonScene") {
            scn = DungeonScene(this, "GameStart")
        }
        if (sceneName == "CastleScene") {
            scn = CastleScene(this, "HyruleScene")
        }
        if (sceneName == "CastleBasementScene") {
            scn = CastleBasementScene(this, "CastleScene")
        }
        if (sceneName == "ArmosScene") {
            scn = ArmosScene(this, "CastleBasementScene")
        }
        if (sceneName == "BattleScene") {
            scn = BattleScene(this, "warp")
        }
        return scn
    }

    fun save() {
        var fos: FileOutputStream? = null
        var out: ObjectOutputStream? = null
        var file: java.io.File = java.io.File("Zelda.ser")
        try {
            file.delete()
        } catch (e: java.lang.Exception) {
        }
        file = java.io.File("Zelda.ser")
        try {
            fos = FileOutputStream(file)
            out = ObjectOutputStream(fos)
            val data = SaveData(link, scene)
            out.writeObject(data)
            out.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

    fun getLink(): Link {
        return link
    }

    @Synchronized
    fun getScene(): Scene? {
        return scene
    }

    @Synchronized
    fun setScene(scene: Scene?) {
        this.scene = scene
    }

    fun setaPressed(aPressed: Boolean) {
        this.aPressed = aPressed
    }

    fun setdPressed(dPressed: Boolean) {
        this.dPressed = dPressed
    }

    fun setjPressed(jPressed: Boolean) {
        this.jPressed = jPressed
    }

    fun setkPressed(kPressed: Boolean) {
        this.kPressed = kPressed
    }

    fun setlPressed(lPressed: Boolean) {
        this.lPressed = lPressed
    }

    fun setsPressed(sPressed: Boolean) {
        this.sPressed = sPressed
    }

    fun setwPressed(wPressed: Boolean) {
        this.wPressed = wPressed
    }

    fun isaPressed(): Boolean {
        return aPressed
    }

    fun isdPressed(): Boolean {
        return dPressed
    }

    fun isjPressed(): Boolean {
        return jPressed
    }

    fun iskPressed(): Boolean {
        return kPressed
    }

    fun islPressed(): Boolean {
        return lPressed
    }

    fun issPressed(): Boolean {
        return sPressed
    }

    fun iswPressed(): Boolean {
        return wPressed
    }
}