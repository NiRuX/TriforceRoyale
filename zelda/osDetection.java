package zelda;

import javax.swing.*;
import java.util.Locale;

public class osDetection {

    public enum OSType {
        Windows, MacOS, Linux, Other
    };

    protected static OSType detectedOS;

    // The purpose of this class is to detect the current Operating System and then figure out where to save
    // The game at a later point when Save files are implemented.
    // Unix/Mac would be kept in .cache/ZelUn for example compared to on Windows it'll be on %APPDATA%/ZelUn
    // So far, calling getOperatingSystemType() in this class will only return the OS Type and whatever
    // You put in the function header.

    public static void getOperatingSystemType() {
        if (detectedOS == null) {
            String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
            if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) {
                detectedOS = OSType.MacOS;
                isMac();
            } else if (OS.indexOf("win") >= 0) {
                detectedOS = OSType.Windows;
                isWin();
            } else if (OS.indexOf("nux") >= 0) {
                detectedOS = OSType.Linux;
                isUnix();
            } else {
                detectedOS = OSType.Other;
                autoDetectionFailed();
            }
        }
    }

    public static void isWin() {
        System.out.println("NT");
        Main.RunGame();
    }

    public static void isMac() {
        System.out.println("XNU");
        Main.RunGame();
    }

    public static void isUnix() {
        System.out.println("UNIX/Linux");
        Main.RunGame();
    }

    public static void autoDetectionFailed() {
        System.out.println("NULL");

        JOptionPane.showMessageDialog(null,
                "System Detection Failed, Report it on Github!",
                "Detection Failure",
                JOptionPane.WARNING_MESSAGE);
        try {
            System.out.println("The osDetection script returned an error, please report it. We will now close the program.");
            Thread.sleep(1000);
            exitSystem();
        } catch (InterruptedException ex) {
            System.out.println("The osDetection script returned an error, please report it. We will now close the program.");
            Thread.currentThread().interrupt();
            exitSystem();
        }
    }

    public static void exitSystem() {
        Runtime.getRuntime().exit(0);
    }
}
