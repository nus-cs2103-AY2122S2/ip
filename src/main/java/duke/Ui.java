package duke;

import duke.task.TaskList;

import java.util.Scanner;

/**
 * Represents a UI class that deals with user input and application output.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class Ui {
    private final Scanner reader = new Scanner(System.in);

    /**
     * Returns a welcome message.
     */
    public static String showWelcome() {
        return "Hello, traveller! My name in Paimon.\n"
                + "How can I helping you today?";
    }

    /**
     * Displays a generic error message about failed data loading from a file.
     */
    public static String showLoadingError() {
        return "Oh no, an error occurred with processing the data "
                + "file :c";
    }

    /**
     * Displays a specific error message about some exception in the program.
     *
     * @param s string describing error
     * @return string describing error
     */
    public String showError(String s) {
        return s;
    }

    public static String showEmptyMessage() {
        return "Paimon cannot read minds!";
    }

    /**
     * Handles display output for commands.
     * Displays the associated message with a given command and prints the list
     * of tasks if necessary.
     *
     * @param command string describing command
     * @param tasks current TaskList of tasks
     * @return response
     */
    public String showCommandMessage(String command, TaskList tasks) {
        switch (command) {
        case "list":
            return "Hmm... Paimon keeps a clear record in her "
                    + "diary.\n" + tasks.toString();
        case "do":
            // Fallthrough
        case "undo":
            return "Task successfully updated.";
        case "delete":
            return "Noted, the task has been scrubbed off the "
                    + "list!\n" + tasks.toString();
        case "todo":
            return "Got it! I have noted down the following task "
                    + "in your list.";
        case "find":
            return "Here are the matching tasks in your list:";
        case "deadline":
            return "Got it! I have noted down the following task in"
                    + " your list. \nRemember the deadline!";
        case "event":
            return "Got it! I have noted down the following task in"
                    + " your list.\nDo be there on time!";
        case "bye":
            reader.close();
            return "Bye, hope to see you again soon!";
        default:
            return "That went over Paimon's head a little...";
        }
    }
}
