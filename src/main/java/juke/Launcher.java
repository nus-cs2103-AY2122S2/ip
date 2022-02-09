package juke;

import javafx.application.Application;

/**
 * Launcher class for Juke application.
 */
public class Launcher {
    /**
     * Entry point main method for the Juke application.
     * Runs Juke CLI if 'cli' is given as the first argument.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("cli")) {
            Juke.main(args);
        } else {
            Application.launch(Juke.class, args);
        }
    }
}
