package duke.ui;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class acts as a interface between the user and the application.
 */
public class Ui {
    private static final String DELIMITER = "*******************************************************";

    private final Scanner in;
    private final PrintStream out;

    /**
     * Class constructor.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = System.out;
    }

    /**
     * Greets the user.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.println(logo);
        out.println("Hello! I'm Duke");
        out.println("What can I do for you?");
    }

    /**
     * Gets a command from the user.
     *
     * @return the command string input by the user.
     */
    public String getCommand() {
        out.println(DELIMITER);
        out.print("Enter your command: ");

        String command = in.nextLine();
        return command;
    }

    /**
     * Says bye to user.
     */
    public void sayBye() {
        out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the message from the application to the user.
     *
     * @param message message to be shown to user.
     */
    public void showMessage(String message) {
        out.println(message);
    }

    /**
     * Displays the error message from the application to the user.
     *
     * @param message error message to be shown to user.
     */
    public void showErrorMessage(String message) {
        out.println("Uh oh! " + message + " :(");
    }
}
