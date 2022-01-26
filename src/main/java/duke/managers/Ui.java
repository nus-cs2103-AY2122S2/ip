package duke.managers;

import duke.external.FastIO;

/**
 * Represents the manager that deals with user interaction.
 * Makes use of an external io program.
 */
public class Ui {

    protected FastIO io;

    /**
     * Creates an instance of Ui.
     */
    public Ui() {
        io = new FastIO();
    }

    /**
     * Returns the current user input.
     *
     * @return a String which represents the line input by the user.
     */
    public String getInput() {
        return io.nextLine();
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
        echo("Hello! I'm Duke\n" +
                "     What can I do for you?");
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
        io.close();
    }

}
