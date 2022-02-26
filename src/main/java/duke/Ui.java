package duke;

import duke.task.TaskList;

/**
 * Represents a UI class that deals with user input and application output.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class Ui {

    /**
     * Returns a welcome message.
     *
     * @return welcome message.
     */
    public static String showWelcome() {
        return "Hello, traveller! My name in Paimon.\n"
                + "How can I helping you today?";
    }

    /**
     * Returns a generic error message about failed data loading from a file.
     *
     * @return error message about data loading errors.
     */
    public static String showLoadingError() {
        return "An error occurred with processing the data file";
    }

    /**
     * Returns a specific error message about some exception in the program.
     *
     * @param s string describing error
     * @return string describing error
     */
    public static String showError(String s) {
        return s;
    }

    /**
     * Returns an error handling message for empty strings.
     */
    public static String showEmptyMessage() {
        return "Paimon cannot read minds!";
    }

    /**
     * Returns string of list of tasks and number of tasks.
     *
     * @param tasks current TaskList
     * @return formatted string of tasks
     */
    public static String showListMessage(TaskList tasks) {
        return "Hmm... Paimon keeps a clear record in her "
                + "diary.\n" + tasks.toString();
    }

    /**
     * Returns generic message for task update.
     *
     * @return string stating that task is updated.
     */
    public static String showUpdateMessage() {
        return "Task successfully updated.";
    }

    /**
     * Returns task deletion message and resulting list of tasks.
     *
     * @param tasks TaskList of tasks
     * @return string of list of tasks and deletion message.
     */
    public static String showDeleteMessage(TaskList tasks) {
        return "Noted, the task has been scrubbed off the "
                + "list!\n" + tasks.toString();
    }

    /**
     * Returns message for the addition of a Todo.
     *
     * @return string stating that a Todo task has been added.
     */
    public static String showTodoMessage() {
        return "Got it! I have noted down the following task "
                + "in your list.\n";
    }

    /**
     * Returns generic message for finding tasks.
     *
     * @return string stating that matching tasks are found.
     */
    public static String showFindMessage() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Returns message for the addition of a Deadline task.
     *
     * @return string stating that a Deadline task has been added.
     */
    public static String showDeadlineMessage() {
        return "Got it! I have noted down the following task in"
                + " your list. \nRemember the deadline!\n";
    }

    /**
     * Returns message for the addition of an Event task.
     *
     * @return string stating that an Event task has been added.
     */
    public static String showEventMessage() {
        return "Got it! I have noted down the following task in"
                + " your list.\nDo be there on time!\n";
    }

    /**
     * Returns message for the end of the program.
     *
     * @return string to end program.
     */
    public static String showByeMessage() {
        return "Bye, hope to see you again soon!";
    }

    /**
     * Returns generic message for indecipherable input.
     *
     * @return generic message about unclear input.
     */
    public static String showDefaultMessage() {
        return "That went over Paimon's head a little...";
    }

    /**
     * Returns generic message for incorrect number input.
     *
     * @return warning message about number input.
     */
    public static String showNumberFormatMessage() {
        return "That went over Paimon's head a little...\n"
                + "You need to type a number instead.";
    }

}
