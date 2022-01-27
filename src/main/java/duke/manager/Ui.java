package duke.manager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import duke.exception.DukeException;


/**
 * Represents an Ui object that handles the user interaction.
 */
public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String INDENTATION = "    ";
    private static final String GREETING = "     Hello! I'm Duke\n" + "     What can I do for you?";

    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        showLine();
        System.out.println(GREETING);
        showLine();
    }

    /**
     * Reads the user input and returns it in the form of a String.
     *
     * @return The user input in the form of a String.
     * @throws DukeException If there is an issue reading user input.
     */
    public String readCommand() throws DukeException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String command = br.readLine();
            return command;
        } catch (IOException e) {
            throw new DukeException("Problem reading input, please give a proper input");
        }
    }

    /**
     * Prints out the error message appended with indentation.
     * @param errMessage The message of the error.
     */
    public void showError(String errMessage) {
        System.out.println(INDENTATION+errMessage);
    }

    /**
     * Prints out a divider line.
     */
    public void showLine() {
        System.out.println(INDENTATION+DIVIDER);
    }

    /**
     * Prints out the message.
     *
     * @param message The message to be printed.
     */
    public void print(String message) {
        System.out.println(INDENTATION+message);
    }

}
