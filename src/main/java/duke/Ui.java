package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represenst a user interface.
 */
public class Ui {

    public static final String WELCOME_MSG = "Hi! I'm Duke.\nWhat can I do for you?";
    public static final String BYE_MSG = "Bye. Hope to see you again soon!";
    public static final String INVALID_MSG = "I don't understand your query! Please try again.";

    /**
     * Constructor for a Ui object.
     */
    public Ui() {}

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println("------------------------------------------------------------------------");
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        this.showLine();
        System.out.println(WELCOME_MSG);
    }

    /**
     * Displays an exit message.
     */
    public void showExit() {
        System.out.println(BYE_MSG);
        this.showLine();
    }

    /**
     * Displays a leading symbol to indicate a user's input.
     */
    public void showLeadingSymbol() {
        System.out.print("> ");
    }

    /**
     * Reads the user's input when making commands.
     *
     * @return the user's full input/command.
     * @throws IOException if there is an error with the input
     */
    public String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }
}
