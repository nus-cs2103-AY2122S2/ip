package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

/** Contains functionality relating to generating Duke's responses */
public class ResponseGenerator {
    private String logo;

    /**
     * Creates a new Ui instance.
     */
    public ResponseGenerator() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    /**
     * Returns a setup message.
     *
     * @return A setup message.
     */
    public String getStartupMessage() {
        StringBuilder s = new StringBuilder();
        s.append("Hello! I'm\n");
        s.append(logo);
        s.append("\nWhat can I do for you? =)");
        return s.toString();
    }

    /**
     * Returns a farewell message.
     *
     * @return A farewell message.
     */
    public String getByeMessage() {
        return "Bye t_t";
    }

    /**
     * Returns an error message for file loading and parsing errors.
     *
     * @return An error message for file loading and parsing errors.
     */
    public String getFileErrorMessage() {
        return "Error loading/parsing file ?.? Creating empty list!";
    }

    /**
     * Returns an error message for I/O errors.
     *
     * @return An error message for I/O errors.
     */
    public String getIoErrorMessage() {
        return "I/O error x.x";
    }

    /**
     * Returns the message to be printed when a task is added to the task list.
     *
     * @param latestTask The task that was just added to the task list.
     * @param size The new size of the task list.
     * @return The message to be printed when a task is added.
     */
    public String getAddTaskMessage(Task latestTask, int size) {
        StringBuilder s = new StringBuilder();
        s.append("added o.O:\n  ");
        s.append(latestTask.toString());
        s.append("\nNow there are " + size + " tasks on the list x)");
        return s.toString();
    }

    /**
     * Prints the items in the task list.
     *
     * @param list The list of tasks in the task list.
     * @return A string containing all the tasks in the task list.
     */
    public String printItems(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "There are no tasks on your list :O";
        }
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks on your list :O\n");
        for (int i = 1; i <= list.size(); i++) {
            s.append(i);
            s.append(". ");
            s.append(list.get(i - 1));
            if (i < list.size()) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    /**
     * Prints the items in the task list found.
     *
     * @param list The list of tasks in the task list.
     * @return A string containing all the tasks in the task list.
     */
    public String printFoundItems(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "No items found :O";
        }
        StringBuilder s = new StringBuilder();
        s.append("Here are the matching tasks on your list :O\n");
        for (int i = 1; i <= list.size(); i++) {
            s.append(i);
            s.append(". ");
            s.append(list.get(i - 1));
            if (i < list.size()) {
                s.append("\n");
            }
        }
        return s.toString();
    }
}
