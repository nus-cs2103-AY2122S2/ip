package duke.managers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents the manager that deals with user interaction.
 * Makes use of an external io program.
 */
public class Ui {

    private BufferedReader br;

    /**
     * Creates an instance of Ui.
     */
    public Ui() {
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    /**
     * Returns the current user input.
     *
     * @return a String which represents the line input by the user.
     */
    public String getInput() {
        String line = "";
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    /**
     * Prints a message to the user with additional formatting.
     *
     * @param msg the message to be printed.
     */
    public void showMessage(String msg) {
        echo(msg);
    }

    /**
     * Prints a greeting message to the user.
     */
    public void greet() {
        echo("Hello! I'm Duke\n"
                + "     What can I do for you?");
    }

    /**
     * Prints a line separator to the user.
     */
    public void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    protected void echo(String input) {
        System.out.println("     " + input);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void bye() {
        echo("Bye. Hope to see you again soon!");
    }

    /**
     * Closes the external io program. To be called only at the end of
     * program execution.
     */
    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
