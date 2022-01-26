package duke.ui;

import duke.tasks.TaskManager;
import duke.exceptions.TaskIndexException;
import duke.tasks.Task;

/**
 * UiManager Object that handles user input and output.
 */
public class UiManager {
    private final String LINE = "-------------------------------------------";

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
        this.showLine();
        System.out.println("Hello there, I'm Larry!");
        this.showLine();
    }

    /**
     * Prints a formatted exit message.
     */
    public void exit() {
        this.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    /**
     * Prints a formatted message when a Task Object is added.
     *
     * @param t Task Object that is added
     * @param i Number of elements stored in TaskManager Object
     */
    public void printAdd(Task t, int i) {
        this.showLine();
        System.out.println("Got it, I added:\n"+ t);
        System.out.printf("Now you have %d item(s) in your list\n", i);
        this.showLine();
    }

    /**
     * Prints a formatted message when a Task Object is marked.
     *
     * @param t Task Object to be printed
     */
    public void printMark(Task t) {
        this.showLine();
        System.out.println("Done? Checked it off for you:");
        System.out.println(t);
        this.showLine();
    }

    /**
     * Prints a formatted message when a Task Object is unmarked.
     *
     * @param t Task Object to be printed
     */
    public void printUnmark(Task t) {
        this.showLine();
        System.out.println("Not done? Let me put it back for you:");
        System.out.println(t);
        this.showLine();
    }

    /**
     * Prints a formatted message when a Task Object is deleted.
     *
     * @param s String representing deleted Task Object
     * @param i Number of elements stored in TaskManager Object
     */
    public void printDelete(String s, int i) {
        this.showLine();
        System.out.println("I removed this task for you:");
        System.out.println(s);
        System.out.printf("Now you have %d items in your list\n", i);
        this.showLine();
    }

    /**
     * Prints a formatted message when TaskList is saved.
     */
    public void printSave() {
        this.showLine();
        System.out.println("List saved in storage!");
        this.showLine();
    }

    /**
     * Prints a formatted message of the Task List stored in the
     * TaskManager Object.
     *
     * @param tm TaskManager Object containing Task List
     */
    public void printList(TaskManager tm) {
        System.out.println(tm);
    }

    /**
     * Prints a formatted message when a saved list is parsed
     * into the TaskManager Object.
     *
     * @param tm TaskManager Object containing Task List
     */
    public void printLoad(TaskManager tm) {
        System.out.println("Loaded your previous list:");
        this.printList(tm);
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
     * @param s String containing error message
     */
    public void errorMessage(String s) {
        this.showLine();
        System.out.println(s);
        this.showLine();
    }

    /**
     * Converts a String into a String array, omitting
     * white space between characters. Checks if String is
     * valid for specific commands.
     *
     * @param s String input to be processed as a command
     * @return String array containing task description and task type
     * @throws TaskIndexException if an invalid String is given
     */
    public String[] parseCommand(String s) throws TaskIndexException {
        s = s.stripLeading();
        s = s.stripTrailing();
        String[] spliced = s.split("\\s+", 2);
        if (spliced.length == 1 &&
                ((spliced[0].equals("todo")) ||
                        (spliced[0].equals("event")) ||
                        (spliced[0].equals("delete")) ||
                        (spliced[0].equals("mark")) ||
                        (spliced[0].equals("unmark")) ||
                        (spliced[0].equals("deadline")))) {
            throw new TaskIndexException("'" +spliced[0] + "'");
        }
        return spliced;
    }
}
