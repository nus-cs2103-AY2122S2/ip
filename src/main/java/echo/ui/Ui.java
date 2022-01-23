package echo.ui;

import java.util.Scanner;

/**
 * This class encapsulates a UI that deals with user interactions.
 */
public class Ui {

    /** Logo of Echo. */
    private static final String LOGO = "U _____ u    ____    _   _      U  ___ u \n"
            + "\\| ___\"|/ U /\"___|  |'| |'|      \\/\"_ \\/ \n"
            + " |  _|\"   \\| | u   /| |_| |\\     | | | | \n"
            + " | |___    | |/__  U|  _  |u .-,_| |_| | \n"
            + " |_____|    \\____|  |_| |_|   \\_)-\\___/  \n"
            + " <<   >>   _// \\\\   //   \\\\        \\\\    \n"
            + "(__) (__) (__)(__) (_\") (\"_)      (__) \n";

    /** A divider to separate user input from Echo's response. */
    private static final String DIVIDER = "____________________________________________________________";

    /** White spaces for better formatting of Echo's response. */
    private static final String PREFIX = "    ";

    /** Scanner to read user input. */
    private final Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message on start of the application.
     */
    public void showWelcome() {
        System.out.println(LOGO);
    }

    /**
     * Prints the goodbye message on exit of the application.
     */
    public void sayBye() {
        System.out.println(PREFIX + "Goodbye!");
        sc.close();
    }

    /**
     * Prints the divider line.
     */
    public void showLine() {
        System.out.println(addPrefix(DIVIDER));
    }

    /**
     * Prints error message.
     *
     * @param s Error message.
     */
    public void showError(String s) {
        System.out.println(addPrefix(s));
    }

    /**
     * Prints a welcome message for new user.
     */
    public void welcomeNewUser() {
        System.out.println(addPrefix("Welcome new user!"));
    }

    /**
     * Read a line of user input.
     *
     * @return String of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Append PREFIX to a string.
     *
     * @return String appended with PREFIX.
     */
    public static String addPrefix(String s) {
        return PREFIX + s;
    }

    /**
     * Prints the task status after adding a task.
     *
     * @param taskStatus String representation of task status.
     * @param size Size of TaskList.
     */
    public void showAdd(String taskStatus, int size) {
        System.out.println(addPrefix("Got it. I've added this task: \n")
                + "  " + addPrefix(taskStatus) + "\n"
                + addPrefix(String.format("Now you have %d tasks in the list.", size)));
    }

    /**
     * Prints a message that the task list is empty.
     */
    public void showEmptyList() {
        System.out.println(addPrefix("Task list is empty!"));
    }

    /**
     * Prints the task list.
     *
     * @param s String representation of task list.
     */
    public void showList(String s) {
        System.out.println(s);
    }

    /**
     * Prints the task status after marking a task.
     *
     * @param taskStatus String representation of task status.
     */
    public void showMark(String taskStatus) {
        System.out.println(addPrefix("Nice! The task is marked as done: \n")
                + "  " + addPrefix(taskStatus));
    }

    /**
     * Prints the task status after unmarking a task.
     *
     * @param taskStatus String representation of task status.
     */
    public void showUnmark(String taskStatus) {
        System.out.println(addPrefix("OK! The task is unmarked: \n")
                + "  " + addPrefix(taskStatus));
    }

    /**
     * Prints the task status after deleting a task.
     *
     * @param taskStatus String representation of task status.
     * @param size Size of TaskList.
     */
    public void showDelete(String taskStatus, int size) {
        System.out.println(addPrefix("Noted. I've removed the task: \n")
                + "  " + addPrefix(taskStatus) + "\n"
                + addPrefix(String.format("Now you have %d tasks in the list.", size)));
    }

    /**
     * Prints a list of task status that matches the user input.
     *
     * @param s String representation of task that matches the user input.
     */
    public void showFind(String s) {
        System.out.println(addPrefix("Here are the matching tasks in your list:\n") + s);
    }

    /**
     * Prints a message that no task fits the description.
     */
    public void showCantFind() {
        System.out.println(addPrefix("No task with that description"));
    }

    /**
     * Prints the list of commands.
     */
    public void showHelp() {
        System.out.println(addPrefix("Commands: \n")
                + addPrefix("list                                | List current tasks. \n")
                + addPrefix("todo <description>                  | Add a todo task. \n")
                + addPrefix("deadline <description> /by <time>   | Add a deadline task to be done before time. \n")
                + addPrefix("event <description> /at <time>      | Add an event task that occurs at time. \n")
                + addPrefix("mark <task number>                  | Mark task as completed. \n")
                + addPrefix("unmark <task number>                | Unmark task as uncompleted. \n")
                + addPrefix("delete <task number>                | Delete task. \n")
                + addPrefix("bye                                 | exit. "));
    }

}
