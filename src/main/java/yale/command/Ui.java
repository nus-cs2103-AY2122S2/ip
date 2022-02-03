package yale.command;

import java.util.Scanner;

/**
 * Class to deal with interactions
 * with the user.
 */
public class Ui {
    private static final String LOGO = "-----YALE-----";
    private static final String WELCOME_MESSAGE = "Allow me to introduce myself\n"
            + LOGO + "\nThe name's yale.Yale.";
    private static final String ENTER_COMMAND = "\nEnter command below:";

    /**
     * Constructor method.
     */
    public Ui() {
    }

    /**
     * Prints out welcome message
     * to user when program starts.
     */
    public void welcomePrompt() {
        System.out.println(WELCOME_MESSAGE);
    }

    public String showExceptionError(Exception e) {
        return e.getMessage();
    }

    /**
     * Method to receive input from the scanner and
     * returns that input in a String format.
     * @param scanner Scanner to read user input.
     * @return Input.
     */
    public String receiveInput(Scanner scanner) {
        System.out.println(ENTER_COMMAND);
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Returns true if user input is equal
     * to "bye" and false otherwise.
     * @param input User input of type String
     * @return True if input equals "bye", false otherwise.
     */
    public boolean checkExit(String input) {
        return input.equals("bye");
    }
}
