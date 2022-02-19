package bob;

/**
 * This class handles all output by the program to the user.
 */
public class Ui {

    /**
     * Greets the user on program startup.
     *
     * @return Greetings to the user.
     */
    public static String greet() {
        return Ui.printLine()
                + "Hello! I'm Bob!\n" + "Loading saved entries...\n"
                + "What can I do for you?\n"
                + "Type \"command\" to see a list of commands.\n"
                + Ui.printLine();
    }

    /**
     * Returns Ui message for archive command.
     *
     * @return Ui message for archive command.
     */
    public static String archive() {
        return Ui.printLine()
                + "Archived all existing entries! Your list is now empty.\n"
                + "You can find your archived entries in /data/bob_archive.txt "
                + "in the directory where Bob is saved.\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message describing all supported commands.
     *
     * @return UI message describing all supported commands.
     */
    public static String command() {
        return Ui.printLine()
                + "Here are all supported commands!\n"
                + "    1. Todo JOB: adds JOB to list of tasks.\n"
                + "    2. Event JOB /at TIME: adds an event called JOB happening at TIME to list of tasks.\n"
                + "    3. Deadline JOB /by TIME: adds a deadline for JOB at TIME to list of tasks.\n"
                + "    4. List: lists all tasks in the list.\n"
                + "    5. Delete INTEGER: deletes the entry indexed by INTEGER from the list.\n"
                + "    6. Mark INTEGER: marks the entry indexed by INTEGER as done.\n"
                + "    7. Unmark INTEGER: marks the entry indexed by INTEGER as not done.\n"
                + "    8. Find KEYWORD: returns a list of entries that contains KEYWORD.\n"
                + "    9. Archive: moves all existing entries to another file and clears the current list.\n"
                + "    10. Command: returns a list of supported commands with descriptions.\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message when program exits.
     *
     * @return UI message when program exits.
     */
    public static String bye() {
        return Ui.printLine()
                + "Bye. Hope to see you again soon!\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message for the list command.
     *
     * @return UI message for the list command.
     */
    public static String list() {
        return Ui.printLine()
                + "Here are the tasks in your list:\n";
    }

    /**
     * Returns UI message when a task is marked.
     *
     * @param task Task to be marked.
     * @return UI message when a task is marked.
     */
    public static String mark(Task task) {
        return Ui.printLine()
                + "Nice! I've marked this task as done:\n    " + task + "\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message when a task is unmarked.
     *
     * @param task Task to be unmarked.
     * @return UI message when a task is unmarked.
     */
    public static String unmark(Task task) {
        return Ui.printLine()
                + "OK, I've marked this task as not done yet:\n" + task + "\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message when a Task is deleted.
     *
     * @param tasksSize New size of TaskList.
     * @param task      Task to be removed.
     * @return UI message when a Task is deleted.
     */
    public static String delete(int tasksSize, Task task) {
        return Ui.printLine()
                + "Noted. I've removed this task:\n    " + task
                + "\nNow you have " + tasksSize + " tasks in the list.\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message when a new Task is added.
     *
     * @param task      Newly added Task.
     * @param tasksSize New size of TaskList.
     * @return UI message when a new Task is added.
     */
    public static String newTask(Task task, int tasksSize) {
        return Ui.printLine()
                + "Got it. I've added this task:\n    " + task + "\n"
                + "Now you have " + tasksSize + " tasks in the list.\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message for a search result.
     *
     * @return UI message for a search result.
     */
    public static String find() {
        return Ui.printLine()
                + "Here are the matching tasks in your list:\n";
    }

    /**
     * Returns UI message when an invalid command is given.
     *
     * @param command Invalid command to be echoed.
     * @return UI message when an invalid command is given.
     */
    public static String invalidCommand(String command) {
        return Ui.printLine()
                + "I don't understand: " + command + "\n"
                + "Please repeat!:(\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message when insufficient arguments are given.
     *
     * @return UI message when insufficient arguments are given.
     */
    public static String insufficientArgs() {
        return Ui.printLine()
                + "Insufficient arguments for this command! :(\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message when no such item exists in the TaskList.
     *
     * @return UI message when no such item exists in the TaskList.
     */
    public static String noSuchItem() {
        return Ui.printLine()
                + "There is no such item! :0\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message when invalid integers are given.
     *
     * @return UI message when invalid integers are given.
     */
    public static String invalidInt() {
        return Ui.printLine()
                + "Please use a valid integer! 12345!\n"
                + Ui.printLine();
    }

    /**
     * Returns UI message when time is in invalid format.
     *
     * @return UI message when time is in invalid format.
     */
    public static String invalidTimeFormat() {
        return Ui.printLine()
                + "Please input the time in the correct format! yyyy-mm-ddThh:mm:ss\n"
                + Ui.printLine();
    }

    /**
     * Returns a line to format output.
     *
     * @return A line in string format.
     */
    public static String printLine() {
        return "----------------------------------------\n";
    }
}
