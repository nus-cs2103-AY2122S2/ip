package duke.ui;

import java.util.Scanner;

/**
 * Text Ui of the program.
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke, What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String DIVIDER = "-------------------------------------------------";
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcomeMessage() {
        showMessage(WELCOME_MESSAGE);
    }

    /**
     * Prints the exit message.
     */
    public String showExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Prompts the user for input.
     *
     * @return The string entered by user.
     */
    public String getUserInput() {
        System.out.print("~~>Enter command: ");
        return sc.nextLine();
    }

    /**
     * Prints a message that follows Ui format.
     *
     * @param message The string message to be printed.
     */
    public String showMessage(String message) {
        return DIVIDER + "\n" + message + "\n" + DIVIDER + "\n";
    }
}
