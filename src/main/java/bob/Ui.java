package bob;

/**
 * This class handles all output by the program to the user.
 */
public class Ui {
    /**
     * Greets the user on program startup.
     */
    public static String greet() {
        return Ui.printLine()
                + "Hello! I'm Bob!\n" + "Loading saved entries...\n"
                + "What can I do for you?\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message when program exits.
     */
    public static String bye() {
        return Ui.printLine()
                + "Bye. Hope to see you again soon!\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message for the list command.
     */
    public static String list() {
        return Ui.printLine()
                + "Here are the tasks in your list:\n";
    }

    /**
     * Prints UI message when a task is marked.
     *
     * @param task Task to be marked.
     */
    public static String mark(Task task) {
        return Ui.printLine()
                + "Nice! I've marked this task as done:\n    " + task + "\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message when a task is unmarked.
     *
     * @param task Task to be unmarked.
     */
    public static String unmark(Task task) {
        return Ui.printLine()
                + "OK, I've marked this task as not done yet:\n" + task + "\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message when a Task is deleted.
     *
     * @param tasksSize New size of TaskList.
     * @param task      Task to be removed.
     */
    public static String delete(int tasksSize, Task task) {
        return Ui.printLine()
                + "Noted. I've removed this task:\n    " + task
                + "\nNow you have " + tasksSize + " tasks in the list.\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message when a new Task is added.
     *
     * @param task      Newly added Task.
     * @param tasksSize New size of TaskList.
     */
    public static String newTask(Task task, int tasksSize) {
        return Ui.printLine()
                + "Got it. I've added this task:\n    " + task + "\n"
                + "Now you have " + tasksSize + " tasks in the list.\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message for a search result.
     */
    public static String find() {
        return Ui.printLine()
                + "Here are the matching tasks in your list:\n";
    }

    /**
     * Prints UI message when an invalid command is given.
     *
     * @param cmd Invalid command to be echoed.
     */
    public static String invalidCommand(String cmd) {
        return Ui.printLine()
                + "I don't understand: " + cmd + "\n"
                + "Please repeat!:(\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message when insufficient arguments are given.
     */
    public static String insufficientArgs() {
        return Ui.printLine()
                + "Insufficient arguments for this command! :(\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message when no such item exists in the TaskList.
     */
    public static String noSuchItem() {
        return Ui.printLine()
                + "There is no such item! :0\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message when invalid integers are given.
     */
    public static String invalidInt() {
        return Ui.printLine()
                + "Please use a valid integer! 12345!\n"
                + Ui.printLine();
    }

    /**
     * Prints UI message when time is in invalid format.
     */
    public static String invalidTimeFormat() {
        return Ui.printLine()
                + "Please input the time in the correct format! yyyy-mm-ddThh:mm:ss\n"
                + Ui.printLine();
    }

    /**
     * Prints a line to format output.
     */
    public static String printLine() {
        return "----------------------------------------\n";
    }
}
