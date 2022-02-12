package duke.ui;

import duke.task.TaskList;

/**
 * Class that deals with user interaction between Duke chatbot and user.
 */
public class Ui {
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static final String[] COMMANDS = {"list", "todo <task>", "deadline <task> /by " + Messages.DATETIME_FORMAT,
                                                "event <task> /at <at>", "mark <taskNumber>",
                                                "unmark <taskNumber>", "delete <taskNumber>", "bye"};

    /**
     * Appends a string to the original string.
     * @param curr the original string.
     * @param add the string to append.
     */
    public static String append(String curr, String add) {
        return curr + add + LINE_SEPARATOR;
    }

    /**
     * Says bye to the user and closes the scanner for system input.
     */
    public String farewell() {
        return showToUser(LINE_SEPARATOR, Messages.FAREWELL_MESSAGE);
    }

    /**
     * Displays the available commands Duke chatbot consists of.
     */
    public String showCommands() {
        String output = "";
        int count = 0;
        for (int i = count; i < COMMANDS.length; i++) {
            count++;
            output += count + ". " + COMMANDS[i] + LINE_SEPARATOR;
        }
        return output;
    }

    /**
     * Prints the number of tasks left.
     *
     * @param tasks The TaskList that consists of the task number we want to retrieve.
     */
    public String printTaskCount(TaskList tasks) {
        return String.format("Now you have %d task(s) in your list.", tasks.getSize());
    }

    /**
     * Prints the message indicating a task has been added.
     *
     * @param tasks The TaskList that consists of the latest task added and the total task count.
     */
    public String printTaskAdded(TaskList tasks) {
        String output = "";
        output = Ui.append(output, "Got it. I've added this task: ");
        output = Ui.append(output, tasks.get(tasks.getSize() - 1).toString());
        output = Ui.append(output, printTaskCount(tasks));
        return output;
    }

    /**
     * Displays an error message to user.
     *
     * @param message The message that is displayed to user.
     */
    public String showError(String message) {
        return showToUser(message);
    }

    /**
     * Prints the input messages line by line to user.
     *
     * @param message The messages that are displayed to the user, can be 1 or more using Varargs.
     */
    public String showToUser(String... message) {
        String output = "";
        for (String m : message) {
            output = Ui.append(output, m);
        }
        return output;
    }
}
