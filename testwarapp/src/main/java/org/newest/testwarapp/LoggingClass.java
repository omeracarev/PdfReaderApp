package org.newest.testwarapp;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingClass {
    private static final Logger LOGGER = Logger.getLogger(LoggingClass.class.getName());
    private ConsoleHandler consoleHandler;

    public LoggingClass() {
        consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);

        LOGGER.addHandler(consoleHandler);
        LOGGER.setLevel(Level.ALL);
    }

    public void logInfo(String message) {
        LOGGER.info(message);
    }

    public void logWarning(String message) {
        LOGGER.warning(message);
    }

    public void logError(String message) {
        LOGGER.severe(message);
    }
}

