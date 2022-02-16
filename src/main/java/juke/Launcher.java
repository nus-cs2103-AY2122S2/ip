package juke;

import javafx.application.Application;

/**
 * Launcher class for the Juke application.
 */
public class Launcher {
    /**
     * Argument to run CLI mode.
     */
    private static final String CLI_ARG_NAME = "cli";

    /**
     * Runs the Juke application.
     * Runs Juke CLI if 'cli' is given as the first argument.
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        boolean hasCliArg = args.length > 0 && args[0].equalsIgnoreCase(CLI_ARG_NAME);
        if (hasCliArg) {
            Juke.main(args);
        } else {
            Application.launch(Juke.class, args);
        }
    }
}
