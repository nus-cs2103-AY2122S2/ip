package duke.ui;

import duke.DukeException;
import duke.common.Messages;
import duke.parser.Parser;
import duke.task.TaskList;

public class TextUi {
    public static final String LINE_BREAK = "----------------------------------\n";
    public static final String EMPTY_LINE = "\n";

    /**
     * Displays the welcome message.
     *
     * @return The welcome message of Duke.
     */
    public static String showWelcome() {
        return showToUser(
                Messages.MESSAGE_WELCOME,
                LINE_BREAK,
                Messages.MESSAGE_PROMPT,
                EMPTY_LINE,
                Messages.COMMAND_SUMMARY);
    }

    public static String showExecutionMessage(String message) {
        return showToUser(message);
    }

    public static String showExecutionMessage(String message, String taskString) {
        return showToUser(message, LINE_BREAK, taskString);
    }

    /**
     * Displays the execution message.
     *
     * @param message The execution message.
     * @param taskString The task object formatted in string.
     * @param size The size of the task list.
     * @return The formatted execution message.
     */
    public static String showExecutionMessage(String message, String taskString, int size) {
        String displaySize = String.format("Now you have %s task%s in the list.\n", size, Parser.parseTaskSize(size));
        return showToUser(message, LINE_BREAK, taskString, EMPTY_LINE, displaySize);
    }

    public static String showError(String message) {
        return showToUser(message);
    }

    /**
     * Displays the task list.
     *
     * @param tasks The task list to be displayed.
     * @return The formatted task list as a string message.
     * @throws DukeException If tasks does not exist, it
     * throws a DukeExceptions.
     */
    public static String showTasks(TaskList tasks) throws DukeException {
        StringBuilder str = new StringBuilder();
        str.append(Messages.MESSAGE_FIND);
        str.append(LINE_BREAK);
        for (int i = 0; i < tasks.getSize(); i++) {
            str.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return str.toString();
    }

    /**
     * Displays the input message to user.
     *
     * @param messages The messages after command execution.
     * @return The formatted messages.
     */
    public static String showToUser(String... messages) {
        StringBuilder str = new StringBuilder();
        for (String m : messages) {
            str.append(m);
        }
        return str.toString();
    }
}
