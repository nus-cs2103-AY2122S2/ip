package duke.ui;

import duke.exceptions.TaskIndexException;
import duke.tasks.Task;
import duke.tasks.TaskManager;

/**
 * UiManager Object that handles user input and output.
 */
public class UiManager {
    private static final String LINE = "-------------------------------------------";

    /**
     * Constructs the UiManager Object.
     */
    public UiManager() {
    }

    /**
     * Prints a formatted line.
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Returns a formatted line in the form of a String.
     *
     * @return a formatted line in the form of a String
     */
    public String getLine() {
        return this.LINE;
    }

    public void start() {
        System.out.println("> Larry started successfully");
    }

    /**
     * Prints a formatted welcome message.
     */
    public String welcome(int i) {
        String message = "Hello there! Let me check if you list saved...\n";
        message += String.format("you currently have %d task(s)!", i);
        return message;
    }

    /**
     * Prints a formatted exit message.
     *
     * @return String containing formatted exit message
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a formatted message when a Task Object is added.
     *
     * @param t Task Object that is added
     * @param i Number of elements stored in TaskManager Object
     * @return String containing formatted message
     */
    public String printAdd(Task t, int i) {
        String s = "Got it, I added:\n" + t + "\n";
        s += String.format("now you have %d items(s) in your list", i);
        return s;
    }

    /**
     * Prints a formatted message when a Task Object is marked.
     *
     * @param t Task Object to be printed
     * @return String containing Task Object
     */
    public String printMark(Task t) {
        String s = "Done? Checked it off for you:\n";
        s += t;
        return s;
    }

    /**
     * Prints a formatted message when a Task Object is unmarked.
     *
     * @param t Task Object to be printed
     * @return String containing Task Object
     */
    public String printUnmark(Task t) {
        String s = "Not done? Let me put it back for you:\n";
        s += t;
        return s;
    }

    /**
     * Prints a formatted message when a Task Object's description is updated.
     *
     * @param task Task object to be printed
     * @return String containing Task Object
     */
    public String printUpdate(Task task) {
        String s = "Update this task:\n";
        s += task;
        return s;
    }


    /**
     * Prints a formatted message when a Task Object is deleted.
     *
     * @param string String representing deleted Task Object
     * @param i Number of elements stored in TaskManager Object
     * @return String containing Task Object
     */
    public String printDelete(String string, int i) {
        String s = "I removed this task for you:\n";
        s += string + "\n";
        s += String.format("now you have %d items(s) in your list", i);
        return s;
    }

    /**
     * Prints a formatted message when TaskList is saved.
     * @return String containing save message
     */
    public String printSave() {
        return "List saved in storage!";

    }

    /**
     * Prints a formatted message of the Task List stored in the
     * TaskManager Object.
     *
     * @param tm TaskManager Object containing Task List
     * @return String containing TaskList
     */
    public String printList(TaskManager tm) {
        return tm.toString();
    }

    /**
     * Prints a formatted message when there is no saved Task List.
     */
    public void printLoadFail() {
        System.out.println("No list saved previously!");
    }


    /**
     * Prints a formatted message with the attached error message.
     *
     * @param string String containing error message
     * @return String containing error message
     */
    public String showErrorMessage(String string) {
        return string;
    }

    /**
     * Prints a formatted message of a task list with
     * tasks containing a keyword.
     *
     * @param tasks the list of tasks in a String format
     * @param taskName the String representation of the keyword
     * @return String containing find message
     */
    public String printFind(String tasks, String taskName) {
        String s = String.format("These are the tasks labeled %s:\n", taskName);
        s += tasks;
        return s;
    }

    /**
     * Converts a String into a String array, omitting
     * white space between characters. Checks if String is
     * valid for specific commands.
     *
     * @param string String input to be processed as a command
     * @return String array containing task description and task type
     * @throws TaskIndexException if an invalid String is given
     */
    public String[] parseCommand(String string) throws TaskIndexException {
        string = string.stripLeading();
        string = string.stripTrailing();
        String[] spliced = string.split("\\s+", 2);
        if (spliced.length == 1
                && ((spliced[0].equals("todo"))
                || (spliced[0].equals("event"))
                || (spliced[0].equals("delete"))
                || (spliced[0].equals("mark"))
                || (spliced[0].equals("unmark"))
                || (spliced[0].equals("update"))
                || (spliced[0].equals("find"))
                || (spliced[0].equals("deadline")))) {
            throw new TaskIndexException("'" + spliced[0] + "'");
        }
        return spliced;
    }
}
