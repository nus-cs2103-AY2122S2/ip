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
     * Prints message to inform the users that task is added into the list.
     *
     * @param task task to be added into the list.
     * @param taskLists the list of task.
     */
    public String showTaskAdded(Task task, ArrayList<Task> taskLists) {
        return "Got it. I've added this task:" + "\n" + task + "\n"
                + "Now you have " + taskLists.size() + " tasks in the list.";
    }

    /**
     * Prints message to inform the users that task is deleted from the list.
     *
     * @param task task to be deleted from the list.
     * @param taskLists the list of task.
     */
    public String showTaskDeleted(Task task, ArrayList<Task> taskLists) {
        return "Noted. I've removed this task:" + "\n" + task + "\n"
                + "Now you have " + taskLists.size() + " tasks in the list.";
    }

    /**
     * Displays the list of tasks the user has.
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
     * Prints message to inform the users that task is mark as complete.
     *
     * @param currTask task to be mark.
     */
    public String showTaskMarked(Task currTask) {
        return "Nice! I've marked this task as done:\n" + currTask;
    }

    /**
     * Prints message to inform the users that task is unmarked as not complete.
     *
     * @param currTask task to be unmarked.
     */
    public String showTaskUnmarked(Task currTask) {
        return "OK, I've marked this task as not done yet:\n" + currTask;
    }

    /**
     * Prints the search results of what the user find.
     *
     * @param resultLists result lists from the search keyword.
     */
    public String displaySearchResult(ArrayList<Task> resultLists) {
        if (resultLists.size() == 0) {
            return "Opps! No such results";
        } else {
            String result = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < resultLists.size(); i++) {
                Task currTask = resultLists.get(i);
                result += i + 1 + ". " + currTask;
            }
            return result;
        }
    }

}
