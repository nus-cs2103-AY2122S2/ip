package duke.admin;

import duke.tasks.Task;

/**
 * Ui is a class that manages the bulk of the user interaction required by the
 * program.
 */
public class Ui {

    /**
     * Prints out a welcome message when the user boots the program.
     */
    public static String showWelcomeMessage() {
        return "Welcome to Duke, your friendly task manager!\n What do you want to do today?";
    }

    /**
     * Prints out a farewell message when the user leaves the
     * program.
     */
    public static String showGoodByeMessage() {
        return "Sayonara!! Hope to see you again soon hehe! :-)";
    }

    /**
     * Prints out a message to let the user know what task has
     * been added and how many tasks there are currently in the task list.
     * @param task the task that has just been added into the task list
     * @param size the size of the task list
     */
    public static String showAddedMessage(Task task, int size) {
        return "Got it. I've added this task:\n" + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Prints out a message to let the user know what task has
     * been deleted and how many tasks there are remaining in the task list.
     * @param task the task that has just been deleted into the task list
     * @param size the size of the task list
     */
    public static String showDeletedMessage(Task task, int size) {
        return "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Prints out a message to let the user know which task has
     * just been marked as done.
     * @param task the task that has just been marked as done
     */
    public static String showMarkedMessage(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString();
    }

    /**
     * Prints out a message to let the user know which task has
     * just been marked as not yet done.
     * @param task the task that has just been marked as not yet done
     */
    public static String showUnmarkedMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task.toString();
    }

    /**
     * Prints out the error message to the user to let the user
     * know why the program cannot run as intended
     * @param errorMessage a message describing the fault
     */
    public static String showErrorMessage(String errorMessage) {
        return "Uh oh... We ran into an error: " + errorMessage;
    }

    public static String listTasks(TaskList tasks) {
        String elementsList = tasks.list();

        if (elementsList.equals("")) {
            return "There are currently no elements in the list!";
        } else {
            String listPreamble = "These are the tasks currently stored in the list:\n";

            return listPreamble + tasks.list();
        }
    }
}
