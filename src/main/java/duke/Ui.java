package duke;

/**
 * The Ui class handles the elements of the user interface to be
 * displayed at startup
 */
public class Ui {
    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String prince = "______       _\n"
            + "| ___ \\     (_)\n"
            + "| |_/ /_ __  _  _ __    ___  ___\n"
            + "|  __/| '__|| || '_ \\  / __|/ _ \\ \n"
            + "| |   | |   | || | | || (__|  __/\n"
            + "\\_|   |_|   |_||_| |_| \\___|\\___|\n";

    /**
     * Used to display an error if a DukeException occurs
     * during the execution of Prince.
     *
     * @param e The DukeException that was thrown.
     */
    String showError(DukeException e) {
        return (e.getMessage() + "\n");
    }
}
