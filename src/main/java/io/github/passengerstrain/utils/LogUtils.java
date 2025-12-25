package io.github.passengerstrain.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("unused")
public class LogUtils {
    private static final Logger LOGGER = Logger.getLogger("Minecraft");

    private static void log(String logMessage, Level logLevel) {
        LOGGER.log(logLevel, "[LandsInvasion] " + logMessage);
    }

    public static void info(String logMessage) {
        LogUtils.log(logMessage, Level.INFO);
    }

    public static void warn(String logMessage) {
        LogUtils.log(logMessage, Level.WARNING);
    }

    public static void severe(String logMessage) {
        LogUtils.log(logMessage, Level.SEVERE);
    }
}
