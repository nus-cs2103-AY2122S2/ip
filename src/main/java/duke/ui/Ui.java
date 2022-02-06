package duke.ui;

import java.util.Scanner;

import duke.task.TaskList;

/**
 * Class that deals with user interaction between Duke chatbot and user.
 */
public class Ui {
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static final String EXCLAMATION = "!";

    public static final String DIVIDER = "================================================================";

    public static final String LINE_PREFIX = "|| ";

    public static final String BOT_NAME = "Feline";

    public static final String COMMANDS = "list, todo, deadline (using /by),"
            + " event (using /at), mark, unmark, delete";

    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Returns the next String input of the user.
     *
     * @return the next input of the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays "=================" divider line in Ui.
     */
    public void divide() {
        showToUser(DIVIDER);
    }

    /**
     * Greets the user
     */
    public void greet() {
        showToUser(DIVIDER, Messages.WELCOME_MESSAGE);
    }

    /**
     * Says bye to the user and closes the scanner for system input.
     */
    public void farewell() {
        showToUser(DIVIDER, Messages.FAREWELL_MESSAGE);
        scanner.close();
    }

    /**
     * Displays the available commands Duke chatbot consists of.
     */
    public void showCommands() {
        showToUser(Messages.UNKNOWN_COMMAND, COMMANDS);
    }

    /**
     * Prints the given message.
     *
     * @param message The message to be printed and displayed to user.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints the number of tasks left.
     *
     * @param tasks The TaskList that consists of the task number we want to retrieve.
     */
    public void printTaskCount(TaskList tasks) {
        print(String.format("Now you have %d task(s) in your list.", tasks.getSize()));
    }

    /**
     * Prints the message indicating a task has been added.
     *
     * @param tasks The TaskList that consists of the latest task added and the total task count.
     */
    public void printTaskAdded(TaskList tasks) {
        print("Got it. I've added this task:");
        print(tasks.get(tasks.getSize() - 1).toString());
        printTaskCount(tasks);
    }

    /**
     * Displays an error message to user.
     *
     * @param message The message that is displayed to user.
     */
    public void showError(String message) {
        showToUser(message);
    }

    /**
     * Prints the input messages line by line to user.
     *
     * @param message The messages that are displayed to the user, can be 1 or more.
     */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m.replace("\n", LINE_SEPARATOR + LINE_PREFIX));
        }
    }
}
