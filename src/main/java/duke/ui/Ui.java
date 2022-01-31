package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Encapsulates the text user interface of Duke, which deals
 * with providing the messages sent from Duke to the user.
 */
public class Ui {


    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo + "\nHello! I'm Duke\n";
    }

    /**
     * Gives an error message when saved data cannot be retrieved.
     *
     * @param errorMessage the details of the error.
     * @return a message from duke indicating a failure to load data.
     */
    public String showLoadingError(String errorMessage) {
        return "There was an error in retrieving saved data:\n" + errorMessage
                + "\nI couldn't find any data to load, so I've created an empty task list.";

    }

    /**
     * Gives a message indicating data has been successfully loaded.
     *
     * @param taskList the task list containing the loaded task data.
     * @return a String containing the latest retrieved task data.
     */
    public String showLoadingSuccess(TaskList taskList) {
        if (taskList.getLength() != 0) {
            return "I've retrieved your latest task list data\n" + showTaskList(taskList);
        }
        return "";
    }

    /**
     * Gives an error message when data cannot be saved into a txt file.
     *
     * @param errorMessage the details of the error.
     * @return a message from duke indicating a failure to save data.
     */
    public String showSavingError(String errorMessage) {
        return "There was an error in saving the data:\n" + errorMessage;
    }

    /**
     * Gives an error message when the user entered an invalid command.
     *
     * @param errorMessage the details of the error.
     * @return a message from duke indicating an invalid command was received.
     */
    public String showCommandError(String errorMessage) {
        return "Oops, there might be an error in the command entered:\n" + errorMessage;
    }

    /**
     * Gives the contents of the task list as a String.
     *
     * @param taskList the task list to be shown.
     * @return a String containing the contents of the task list.
     */
    public String showTaskList(TaskList taskList) {
        return "Here are the tasks in your list:\n" + taskList;
    }

    /**
     * Gives a message indicating a task was successfully added.
     *
     * @param task the task that was added.
     * @param taskList the task list to which the task was added.
     * @return a message from duke to show a task was added.
     */
    public String showTaskAdded(Task task, TaskList taskList) {
        return "Got it. I've added this task:\n" + task
                + "\nNow you have " + taskList.getLength() + " tasks in the list.";
    }

    /**
     * Gives a message to show a task was marked as complete.
     *
     * @param task the task that was marked as complete.
     * @return a message from duke to show a task was marked.
     */
    public String showTaskMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Gives a message to show a task was marked as incomplete.
     *
     * @param task the task that was marked as incomplete.
     * @return a message from duke to show a task was unmarked.
     */
    public String showTaskUnmarked(Task task) {
        return "OK, I've marked this task as not done yet\n" + task;
    }

    /**
     * Gives a message to show a task was deleted.
     *
     * @param task the task that was deleted.
     * @param taskList the task list from which the task was deleted.
     * @return a message from duke to show a task was deleted.
     */
    public String showTaskDeleted(Task task, TaskList taskList) {
        return "Noted. I've removed this task:\n" + task
                + "\nNow you have " + taskList.getLength() + " tasks in the list.";
    }

    /**
     * Gives the search results of a FindCommand execution.
     *
     * @param findResults the tasks found, in the form of a task list.
     * @return the search results of the FindCommand execution.
     */
    public String showFindResult(TaskList findResults) {
        return "Here are the matching tasks in your task list:\n" + findResults;
    }

    /**
     * Gives a goodbye message.
     */
    public String showGoodbye() {
        return "Bye! Hope to see you again!";
    }
}
