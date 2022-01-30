package luke.ui;

import java.util.Scanner;

/**
 * Implements the text-based user interface.
 */
public class Ui {
    private static final String LOGO = "  _           _        \n"
            + " | |         | |       \n"
            + " | |    _   _| | _____ \n"
            + " | |   | | | | |/ / _ \\\n"
            + " | |___| |_| |   <  __/\n"
            + " |______\\__,_|_|\\_\\___|\n";

    private static final String BORDER = "=".repeat(60);
    private static final String ERROR_MESSAGE_FORMAT = "Oops, the force has encountered an error:\n%s";
    private final Scanner inputSc;

    /**
     * Constructs a user interface.
     */
    public Ui() {
        inputSc = new Scanner(System.in);
    }

    /**
     * Shows the greeting message.
     */
    public void greeting() {
        System.out.println("Hello! I am \n" + LOGO);
        showOutput("How can I help you?");
    }

    /**
     * Shows the specific message, formatted with borders.
     * @param output The message to show.
     */
    public void showOutput(String output) {
        String msg = String.format("%s\n%s\n%s", BORDER, output, BORDER);
        System.out.println(msg);
    }

    /**
     * Closes the user interface.
     */
    public void close() {
        inputSc.close();
    }

    /**
     * Returns the next line of input entered by the user.
     * @return The next line of input from the user.
     */
    public String readInput() {
        return inputSc.nextLine();
    }

    /**
     * Displays the error message formatted with the chat bot's personality.
     * @param message The error message to display.
     */
    public void showError(String message) {
        showOutput(String.format(ERROR_MESSAGE_FORMAT, message));
    }
}
