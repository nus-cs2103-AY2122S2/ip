package duke;

/**
 * The Ui class handles the elements of the user interface to be
 * displayed at startup
 */
public class Ui {
    private static final String divider = "~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String prince = "______       _\n"
            +  "| ___ \\     (_)\n"
            + "| |_/ /_ __  _  _ __    ___  ___\n"
            + "|  __/| '__|| || '_ \\  / __|/ _ \\ \n"
            + "| |   | |   | || | | || (__|  __/\n"
            + "\\_|   |_|   |_||_| |_| \\___|\\___|\n";

    /**
     * Constructor for Ui
     */
    public Ui() {
        System.out.println("Hello I'm\n" + prince);
        System.out.println("How can I help you today?");
        System.out.println(divider);
    }

    /**
     * Prints the divider that separates the output from a new command
     */
    void printDivider() {
        System.out.println(divider);
    }

    /**
     * Used to display an error if a DukeException occurs
     * during the execution of Prince.
     *
     * @param e The DukeException that was thrown
     */
    void showError(DukeException e) {
        System.out.println(e.getMessage());
        System.out.println(divider);
    }
}
