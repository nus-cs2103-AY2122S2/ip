package duke;

import java.util.Scanner;

import duke.task.TaskList;


/**
 * Represents a UI class that deals with user input and application output.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 0.1
 */
public class Ui {
    Scanner reader = new Scanner(System.in);

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello, traveller! My name in Paimon.\nHow can I help you today?");
    }

    /**
     * Displays a goodbye message and closes the associated Scanner.
     */
    public void showGoodbye() {
        System.out.println("Bye, hope to see you again soon!");
        reader.close();
    }

    /**
     * Formats the input line, reads and returns the next line of user input.
     *
     * @return user input
     */
    public String readInput() {
        System.out.print("| \r");
        return reader.nextLine();
    }

    /**
     * Displays a generic error message about failed data loading from a file.
     */
    public void showLoadingError() {
        System.out.println("Oh no, an error occurred with processing the data file :c");
    }

    /**
     * Displays a specific error message about some exception in the program.
     */
    public void showError(String s) {
        System.out.println("Oh no, the following error occurred while running the program:");
        System.out.println(s);
    }

    /**
     * Handles display output for commands.
     * Displays the associated message with a given command and prints the list of tasks if necessary.
     */
    public void showCommandMessage(String command, TaskList tasks) {
        switch (command) {
        case "list":
            System.out.println("Hmm... Paimon keeps a clear record in her diary.");
            System.out.println(tasks);
            break;
        case "do":
        case "undo":
            System.out.println("Task successfully updated.");
            break;
        case "delete":
            System.out.println("Noted, the task has been scrubbed off the list!");
            System.out.println(tasks);
            break;
        case "todo":
            System.out.println("Got it! I have noted down the following task in your list.");
            break;
        case "deadline":
            System.out.println("Got it! I have noted down the following task in your list. " +
                    "\nRemember the deadline!");
            break;
        case "event":
            System.out.println("Got it! I have noted down the following task in your list. " +
                    "\nDo be there on time!");
            break;
        case "bye":
            showGoodbye();
            break;
        default:
            System.out.println("That went over Paimon's head a little...");
        }
    }
}
