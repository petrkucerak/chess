package cz.cvut.fel.pjv.Utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private static void turnLogOff() {
        Logger.getLogger("").setLevel(Level.OFF);
        Logger.getLogger("").getHandlers()[0].setLevel(Level.OFF);
    }

    private static void turnLogOn() {
        Logger.getLogger("").setLevel(Level.INFO);
        Logger.getLogger("").getHandlers()[0].setLevel(Level.INFO);
    }
}
