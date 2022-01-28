package karen;

import karen.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles receiving and formatting of input and output
 */
public class Ui {
    public static final String STD_DIVIDER = "~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*";
    public static final String ERR_DIVIDER = "------------------------------------------------";
    public static final String WELCOME = "Hello, my name is Karen.\nI'll be speaking (to) as your manager today.";
    public static final String GOODBYE = "Goodbye - I'll be seeing your manager's manager next.\nI'll remember this.";
    public static final String NO_TASKS = "Nothing is even added yet.";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Gets formatted string of item at (1-based) index.
     * Example: 1.[T][ ] example-task
     * @param index (1-based) index of item
     * @param item String object representation of object
     * @return Formatted string object representation of item at index.
     */
    protected String formatIndexItem(int index, String item) {
        return String.format("%d.%s\n", index, item);
    }

    /**
     * Gets formatted string of item, action applied to item and the current
     * count of objects.
     * Example: Fine. Task added:\n[T][ ] example-task\nNow you have 1 in total.
     * @param action Action applied to object eg. added, deleted, marked.. etc
     * @param item Task object with action applied to
     * @param count Current count of Tasks in Storage
     * @return Formatted string
     */
    public String formatCount(String action, Task item, int count) {
        return String.format("Fine. Task %s:\n %s\nNow you have %d in total.",
                action, item.toString(), count);
    }

    /**
     * Gets input from user.
     * @return String input from user
     */
    public String readInput() {
        String fullCommand = this.in.nextLine();
        return fullCommand;
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        // more customisation and cleanup in future
        out.println(STD_DIVIDER);
        out.println(WELCOME);
        out.println(STD_DIVIDER);
        out.print("\n");
    }

    /**
     * Prints goodbye message
     */
    public void showGoodbye() {
        // more customisation and cleanup in future
        out.println(STD_DIVIDER);
        out.println(GOODBYE);
        out.println(STD_DIVIDER);
        out.print("\n");
    }

    /**
     * Prints message for inputs and outputs with STD_DIVIDER wrapped around.
     * @param message Message generated from determined inputs and subsequent outputs.
     */
    public void displayUserInput(String message) {
        out.println(STD_DIVIDER);
        out.println(message);
        out.println(STD_DIVIDER);
        out.print("\n");
    }

    /**
     * Prints warning messages for user during runtime resulting from
     * missing configurations etc.
     * @param message Message generated
     */
    public void displayWarning(String message) {
        out.println(ERR_DIVIDER);
        out.println(message);
        out.println(ERR_DIVIDER);
        out.print("\n");

    }

    /**
     * Prints formatted output for displaying taskList objects in
     * 1-based index formatting
     * @param taskList ArrayList object for print out
     */
    public void displayTaskList(ArrayList<Task> taskList) {
        StringBuilder formatString = new StringBuilder();
        int index = 1;
        for (Task item: taskList) {
            formatString.append(formatIndexItem(index, item.toString()));
            index++;
        }
        if (taskList.size()==0) {
            formatString.append(NO_TASKS);
        }

        displayUserInput(formatString.toString());
    }

}
