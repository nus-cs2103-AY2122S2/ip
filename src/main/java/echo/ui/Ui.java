package echo.ui;

/**
 * This class encapsulates a UI that deals with user interactions.
 */
public class Ui {

    /**
     * Returns the opening message.
     *
     * @return Opening message.
     */
    public String getOpeningMessage() {
        return "Hi, my name is Echo, what can I do for you?";
    }

    /**
     * Returns error message.
     *
     * @param errorMessage Error message.
     *
     * @return String of error message.
     */
    public String getErrorMessage(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns a message that the task list is empty.
     *
     * @return String message for empty task list.
     */
    public String getEmptyListMessage() {
        return "Task list is empty!";
    }

    /**
     * Returns the task status after adding a task.
     *
     * @param taskStatus String representation of task status.
     * @param size Size of TaskList.
     *
     * @return String message that task is added.
     */
    public String getAddMessage(String taskStatus, int size) {
        return "Got it. I've added this task: \n"
                + "  " + taskStatus + "\n"
                + String.format("Now you have %d tasks in the list.", size);
    }

    /**
     * Returns the task status after marking a task.
     *
     * @param taskStatus String representation of task status.
     *
     * @return String message that task is marked.
     */
    public String getMarkMessage(String taskStatus) {
        return "Nice! The task is marked as done: \n" + "  " + taskStatus;
    }

    /**
     * Returns the task status after unmarking a task.
     *
     * @param taskStatus String representation of task status.
     *
     * @return String message that task is marked.
     */
    public String getUnmarkMessage(String taskStatus) {
        return "OK! The task is unmarked: \n" + "  " + taskStatus;
    }

    /**
     * Returns the task status after deleting a task.
     *
     * @param taskStatus String representation of task status.
     * @param size Size of TaskList.
     *
     * @return String message that task is deleted.
     */
    public String getDeleteMessage(String taskStatus, int size) {
        return "Noted. I've removed the task: \n"
                + "  " + taskStatus + "\n"
                + String.format("Now you have %d tasks in the list.", size);
    }

    /**
     * Returns the task list.
     *
     * @param s String representation of task list.
     *
     * @return String representation of task list.
     */
    public String getListMessage(String s) {
        return s;
    }


    /**
     * Returns a list of task status that matches the user input.
     *
     * @param s String representation of task that matches the user input.
     *
     * @return String message of task that matches the user input.
     */
    public String getFindMessage(String s) {
        return "Here are the matching tasks in your list:\n" + s;
    }

    /**
     * Prints a message that no task fits the description.
     *
     * @return String message that task could not be found.
     */
    public String getCantFindMessage() {
        return "No task with that description";
    }

    /**
     * Prints the list of commands.
     *
     * @return String message showing commands available.
     */
    public String getHelpMessage() {
        return "Commands: \n"
                + "- list \n"
                + "- todo <description> \n"
                + "- deadline <description> /by <time> \n"
                + "- event <description> /at <time> \n"
                + "- mark <task number> \n"
                + "- unmark <task number> \n"
                + "- delete <task number> \n"
                + "- find <description> \n"
                + "- bye";
    }

    /**
     * Returns the goodbye message on exit of the application.
     *
     * @return String message goodbye.
     */
    public String getByeMessage() {
        return "Goodbye!";
    }
}
