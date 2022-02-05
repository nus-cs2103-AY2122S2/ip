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

    /**
     * Prints a formatted welcome message.
     */
    public void welcome() {
        System.out.println("> Larry started successfully");
    }

    /**
     * Prints a formatted exit message.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints a formatted message when a Task Object is added.
     *
     * @param t Task Object that is added
     * @param i Number of elements stored in TaskManager Object
     */
    public String printAdd(Task t, int i) {
        String s = "Got it, I added:\n" + t + "\n";
        s += "Now you have " + i + " item(s) in your list";
        return s;
    }

    /**
     * Prints a formatted message when a Task Object is marked.
     *
     * @param t Task Object to be printed
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
     */
    public String printUnmark(Task t) {
        String s = "Not done? Let me put it back for you:\n";
        s += t;
        return s;
    }


    /**
     * Prints a formatted message when a Task Object is deleted.
     *
     * @param string String representing deleted Task Object
     * @param i Number of elements stored in TaskManager Object
     */
    public String printDelete(String string, int i) {
        String s = "I removed this task for you:\n";
        s += string + "\n";
        s += "Now you have " + i + " item(s) in your list";
        return s;
    }

    /**
     * Prints a formatted message when TaskList is saved.
     */
    public String printSave() {
        return "List saved in storage!";

    }

    /**
     * Prints a formatted message of the Task List stored in the
     * TaskManager Object.
     *
     * @param tm TaskManager Object containing Task List
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
     */
    public String printFind(String tasks, String taskName) {
        String s = "These are the tasks labeled " + taskName + ":\n";
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
                || (spliced[0].equals("find"))
                || (spliced[0].equals("deadline")))) {
            throw new TaskIndexException("'" + spliced[0] + "'");
        }
        return spliced;
    }
}
