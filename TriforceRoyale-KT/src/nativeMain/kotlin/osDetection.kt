package zelda

object osDetection {
    internal var detectedOS: OSType? = null

    // The purpose of this class is to detect the current Operating System and then figure out where to save
    // The game at a later point when Save files are implemented.
    // Unix/Mac would be kept in .cache/ZelUn for example compared to on Windows it'll be on %APPDATA%/ZelUn
    // So far, calling getOperatingSystemType() in this class will only return the OS Type and whatever
    // You put in the function header.
    val operatingSystemType: Unit
        get() {
            if (detectedOS == null) {
                val OS: String = java.lang.System.getProperty("os.name", "generic").lowercase()
                if (OS.indexOf("mac") >= 0 || OS.indexOf("darwin") >= 0) {
                    detectedOS = OSType.MacOS
                    isMac
                } else if (OS.indexOf("win") >= 0) {
                    detectedOS = OSType.Windows
                    isWin
                } else if (OS.indexOf("nux") >= 0) {
                    detectedOS = OSType.Linux
                    isUnix
                } else {
                    detectedOS = OSType.Other
                    autoDetectionFailed()
                }
            }
        }

    val isWin: Unit
        get() {
            println("Win32/Win64 System")
            Main.RunGame()
        }
    val isMac: Unit
        get() {
            println("XNU System")
            Main.RunGame()
        }
    val isUnix: Unit
        get() {
            println("I run arch")
            Main.RunGame()
        }

    fun autoDetectionFailed() {
        println("Unknown System")
        JOptionPane.showMessageDialog(
            null,
            "System Detection Failed, Report it on Github!",
            "Detection Failure",
            JOptionPane.WARNING_MESSAGE
        )
        try {
            println("The osDetection script returned an error, please report it. We will now close the program.")
            java.lang.Thread.sleep(1000)
            exitSystem()
        } catch (ex: InterruptedException) {
            println("The osDetection script returned an error, please report it. We will now close the program.")
            java.lang.Thread.currentThread().interrupt()
            exitSystem()
        }
    }

    fun exitSystem() {
        java.lang.Runtime.getRuntime().exit(0)
    }

    enum class OSType {
        Windows, MacOS, Linux, Other
    }
}