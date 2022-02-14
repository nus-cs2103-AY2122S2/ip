package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A class that reads user input and displays output to the user.
 */
public class Ui {

    /**
     * Default constructor of Ui
     */
    public Ui() {
    }

    /**
     * Prints a line.
     */
    public void showLine() {
        System.out.println("__________________________________________________");
    }

    /**
     * Prints the duke greeting.
     */
    public void showGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints an error message if there is no pre-existing data found.
     */
    public void showLoadingError() {
        System.out.println("No pre-existing data found!");
    }

    /**
     * Converts user input into a string.
     *
     * @return a String representing the user input.
     * @throws IOException if an I/O error occurs.
     */
    public String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    /**
     * Prints out a given message.
     *
     * @param message a String representing the given message to printed.
     */
    public String showMessage(String message) {
        return message;
    }

    /**
     * Prints out the goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }
}
