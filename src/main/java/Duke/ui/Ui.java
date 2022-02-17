package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints greeting message when user run the program.
     */
    public String showWelcome() {
        return "Hello! I'm Duke\n" + "What can I do for you?";
    }

    /**
     * Prints dash lines. For format purpose.
     */
    public String showLine() {
        return "----------------------------------";
    }

    /**
     * Prints loading error message.
     */
    public void showLoadingError() {
        System.out.println("Opps! There is loading error :<");
    }

    /**
     * Prints error message that was thrown in other class.
     */
    public String showError(String errorMsg) {
        return errorMsg;
    }

    /**
     * Prints goodbye message when user exit the program.
     */
    public String showGoodBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns message to inform the users that task is added into the list.
     *
     * @param task task to be added into the list.
     * @param taskLists the list of task.
     * @param hasAdded true if the task has added into task list. Else false.
     * @return the message to inform the users if the task is added into list.
     */
    public String showTaskAdded(Task task, ArrayList<Task> taskLists, boolean hasAdded) {
        if (hasAdded) {
            return "Got it. I've added this task:" + "\n" + task + "\n"
                    + "Now you have " + taskLists.size() + " tasks in the list.";
        } else {
            return "Opps! The same task added multiple times!";
        }
    }

    /**
     * Returns message to inform the users that task is deleted from the list.
     *
     * @param task task to be deleted from the list.
     * @param taskLists the list of task.
     * @return the message to inform the users that task is deleted from the list.
     */
    public String showTaskDeleted(Task task, ArrayList<Task> taskLists) {
        return "Noted. I've removed this task:" + "\n" + task + "\n"
                + "Now you have " + taskLists.size() + " tasks in the list.";
    }

    /**
     * Displays the list of tasks the user has.
     *
     * @param taskLists the list of task to show to the user.
     * @return the message displaying the list of task in the list.
     */
    public String showList(ArrayList<Task> taskLists) {
        if (taskLists.size() == 0) {
            return "No task for now";
        } else {
            String result = "Here are the tasks in your list:\n";
            for (int i = 1; i < taskLists.size() + 1; i++) {
                Task currTask = taskLists.get(i - 1);
                result += i + ". " + currTask + "\n";
            }
            return result;
        }
    }

    /**
     * Returns message to inform the users that task is mark as complete.
     *
     * @param currTask task to be mark.
     * @return message to inform the users that task is mark as complete.
     */
    public String showTaskMarked(Task currTask) {
        return "Nice! I've marked this task as done:\n" + currTask;
    }

    /**
     * Returns message to inform the users that task is unmarked as not complete.
     *
     * @param currTask task to be unmarked.
     * @return message to inform the users that task is unmarked as not complete.
     */
    public String showTaskUnmarked(Task currTask) {
        return "OK, I've marked this task as not done yet:\n" + currTask;
    }

    /**
     * Returnss the search results of what the user find.
     *
     * @param results result list from the search keyword.
     * @return the search results of what the user find.
     */
    public String displaySearchResult(ArrayList<Task> results) {
        if (results.size() == 0) {
            return "Opps! No such results";
        } else {
            String result = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < results.size(); i++) {
                Task currTask = results.get(i);
                result += i + 1 + ". " + currTask;
            }
            return result;
        }
    }

}
