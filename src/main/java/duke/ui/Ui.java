package duke.ui;

import duke.common.DukeException;

import java.util.Scanner;

/**
 * Class responsible for the User Interface of the Chat bot.
 */
public class Ui {

    /** Logo of the Chat bot. */
    private static final String LOGO = "888      888     888  .d8888b.         d8888 \n"
            + "888      888     888 d88P  Y88b       d88888 \n"
            + "888      888     888 888    888      d88P888 \n"
            + "888      888     888 888            d88P 888 \n"
            + "888      888     888 888           d88P  888 \n"
            + "888      888     888 888    888   d88P   888 \n"
            + "888      Y88b. .d88P Y88b  d88P  d8888888888 \n"
            + "88888888  \"Y88888P\"   \"Y8888P\"  d88P     888 \n";

    /** The divider line used to wrap string before output. */
    private static final String DIVIDER_LINE = "___________________"
            + "_________________________________________\n";

    /** Scanner used for user input. */
    private Scanner scanner;

    /** Constructor to create Ui object. */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Formats the string by indenting with 4 spaces
     * and wrapping the string with divider lines.
     *
     * @param input string to be formatted.
     * @return indented string with divider lines.
     */
    private static String formatString(String input) {
        String indented = input.replaceAll("(?m)^", "    ");
        return indented;
    }

    /**
     * Prints out the exception message.
     *
     * @param exception exception holding the error message.
     */
    public void showError(DukeException exception) {
        System.out.println(
                formatString(exception.getMessage())
        );
    }

    /**
     * Prints out the welcome message of the Chat bot.
     */
    public void showWelcome() {
        System.out.println(
                formatString("\n" + LOGO + "\nHi! I'm Luca\n"
                        + "How may I help you?\n\n" + DIVIDER_LINE)
        );
    }

    /**
     * Print outs the Divider line.
     */
    public void showLine() {
        System.out.println(formatString(DIVIDER_LINE));
    }

    /**
     * Returns the user command as a string.
     */
    public String readCommand() {
        String inputCommand = scanner.nextLine();
        return inputCommand;
    }

    /**
     * Prints out the given message.
     *
     * @param message string to be printed.
     */
    public void showMessage(String message) {
        System.out.println(
          formatString(message)
        );
    }

    /**
     * Prints out the exit message.
     */
    public void showExit() {
        System.out.println(
                formatString("Bye. See you again soon!")
        );
    }

    /**
     * Closes the input scanner.
     */
    public void closeScanner() {
        scanner.close();
    }
}
