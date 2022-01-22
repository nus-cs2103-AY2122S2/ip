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
     * Prints the welcome message upon the start of the application.
     */
    public void showWelcome() {
        System.out.println(LOGO);
    }

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

    public String readCommand() {
        return sc.nextLine();
    }

    public static String addPrefix(String s) {
        return PREFIX + s;
    }

    public void showAddSuccess(String taskStatus, int size) {
        System.out.println(addPrefix("Got it. I've added this task: \n")
                + "  " + addPrefix(taskStatus) + "\n"
                + addPrefix(String.format("Now you have %d tasks in the list.", size)));
    }

    public void showEmptyList() {
        System.out.println(addPrefix("Task list is empty!"));
    }

    public void showList(String s) {
        System.out.println(addPrefix(s));
    }

    public void showMark(String taskStatus) {
        System.out.println(addPrefix("Nice! The task is marked as done: \n")
                + "  " + addPrefix(taskStatus));
    }

    public void showUnmark(String taskStatus) {
        System.out.println(addPrefix("OK! The task is unmarked: \n")
                + "  " + addPrefix(taskStatus));
    }

    public void showDelete(String taskStatus, int size) {
        System.out.println(addPrefix("Noted. I've removed the task: \n")
                + "  " + addPrefix(taskStatus) + "\n"
                + addPrefix(String.format("Now you have %d tasks in the list.", size)));
    }

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
