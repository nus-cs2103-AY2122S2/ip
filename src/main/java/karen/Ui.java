package karen;

import java.util.ArrayList;

import karen.task.Task;

/**
 * Handles receiving and formatting of input and output
 * Partially adapted from: https://github.com/se-edu/addressbook-level2
 */
public class Ui {
    public static final String WELCOME = "Hello, my name is Karen.\nI'll be speaking (to) as your manager today.";
    public static final String GOODBYE = "Goodbye - I'll be seeing your manager's manager next.\nI'll remember this.";
    public static final String NO_TASKS = "Nothing can be found.";

    /**
     * Gets formatted string of item at (1-based) index.
     * Example: 1.[T][ ] example-task
     * @param index (1-based) index of item
     * @param item Task object
     * @return Formatted string object representation of item at index.
     */
    protected String formatIndexTask(int index, Task item) {
        return String.format("%d.%s\n", index, item.toString());
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
     * Prints message for inputs and outputs with STD_DIVIDER wrapped around.
     * @param message Message generated from determined inputs and subsequent outputs.
     */
    public String displayUserInput(String message) {
        return String.format("%s\n\n", message);
    }

    /**
     * Prints warning messages for user during runtime resulting from
     * missing configurations etc.
     * @param message Message generated
     */
    public String displayWarning(String message) {
        return String.format("%s\n\n", message);
    }

    /**
     * Formats String representation of Task object(s) in taskList in 1-based index formatting.
     * @param taskList
     * @return Formatted String representation of taskList
     */
    public String formatTaskList(ArrayList<Task> taskList) {
        StringBuilder formatString = new StringBuilder();
        int index = 1;
        for (Task item: taskList) {
            formatString.append(formatIndexTask(index, item));
            index++;
        }

        // If the taskList is empty, return with default message
        if (taskList.size() == 0) {
            formatString.append(NO_TASKS);
        }
        return formatString.toString();
    }

}
