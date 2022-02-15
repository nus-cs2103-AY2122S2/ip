package duke.gui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.ErrorString;
import duke.task.Task;

/**
 * Ui deals with interactions with the user.
 *
 * @author yl-ang
 */
public class Ui {

    private Scanner sc;

    /**
     * Constructs an Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns the specified error message.
     *
     * @param errorMessage ErrorMessage specified error message.
     * @return errorMessage ErrorMessage specified error message.
     */
    public String errorResponse(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns the error loading message.
     *
     * @return Error loading message.
     */
    public String loadingErrorResponse() {
        return ErrorString.ERROR_LOADING_ERROR.toString();
    }

    /**
     * Returns the specified deleted task formatted string and number of tasks remaining.
     *
     * @param deletedTask DeletedTask the specified deleted task.
     * @param tasks Tasks the remaining tasks.
     * @return Formatted deletedTask string.
     */
    public String deletedTasksResponse(Task deletedTask, ArrayList<Task> tasks) {
        String output = String.format("Noted. I've removed this task:\n       %s\n"
                + "Now you have %s tasks in the list.\n", deletedTask.toString(), tasks.size());
        return output;
    }

    /**
     * Returns the specified added task formatted string and number of tasks.
     *
     * @param addedTask The newly added task.
     * @param tasks The list of all the tasks after adding.
     * @return Formatted addedTask string.
     */
    public String addedTasksResponse(Task addedTask, ArrayList<Task> tasks) {
        assert addedTask != null : "addedTask should not be null";
        String output = String.format("Got it. I've added this task:\n       %s\n"
                + "Now you have %s tasks in the list.\n", addedTask.toString(), tasks.size());
        return output;
    }

    /**
     * Returns the formatted string of the tasks the user have currently.
     *
     * @param tasks The list containing the current tasks.
     * @return Formatted task list string.
     */
    public String listResponse(ArrayList<Task> tasks) {
        assert tasks != null : "tasks should not be null";
        String output = "Here are the tasks in your list:\n";
        int numItems = tasks.size();
        for (int i = 0; i < numItems; i++) {
            output += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Returns the formatted string of the marked task.
     *
     * @param selectedTask SelectedTask task marked by the user.
     * @return Formatted marked task string.
     */
    public String markResponse(Task selectedTask) {
        assert selectedTask != null : "selectedTask to be marked should not be null";
        String output = "Nice! I've marked this task as done:\n";
        output += selectedTask.toString() + "\n";
        return output;
    }

    /**
     * Returns the formatted string of the unmarked task.
     *
     * @param selectedTask SelectedTask task unmarked by the user.
     * @return Formatted unmarked task string.
     */
    public String unmarkResponse(Task selectedTask) {
        assert selectedTask != null : "selectedTask to be unmarked should not be null";
        String output = "OK, I've marked this task as not done:\n";
        output += selectedTask.toString() + "\n";
        return output;
    }

    /**
     * Returns the formatted string of the tasks that contains the search keyword.
     *
     * @param taskLst The current list of tasks.
     * @return Formatted list of tasks string that contains the search keyword.
     */
    public String findResponse(ArrayList<Task> taskLst) {
        assert taskLst != null : "taskLst should not be null";
        String output = "Here are the matching tasks in your list:\n";
        int numItems = taskLst.size();
        for (int i = 0; i < numItems; i++) {
            output += (i + 1) + ". " + taskLst.get(i).toString() + "\n";
        }
        return output;
    }

    /**
     * Returns the formatted string of the updated task containing the updated information.
     *
     * @param task The updated task.
     * @return Formatted string of the updated task.
     */
    public String updateResponse(Task task) {
        String output = "I've updated this task:\n";
        output += task.toString() + "\n";
        return output;
    }

    /**
     * Returns the bye string.
     *
     * @return Bye string.
     */
    public String exitResponse() {
        return "Bye. Hope to see you again soon!\n";
    }
}
