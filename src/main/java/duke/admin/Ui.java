package duke.admin;

import duke.tasks.Task;
import duke.tasks.Trigger;

/**
 * Ui is a class that manages the bulk of the user interaction required by the
 * program.
 */
public class Ui {
    private static final Trigger noChangeTrigger = new Trigger("n0 cH4Ng3$ d#t3CtEd");

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
     * @param tasks the list of task being managed by Duke
     */
    public static String showAddedMessage(Task task, TaskList tasks) {
        return "Got it. I've added this task:\n" + task.toString()
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Prints out a message to let the user know what task has
     * been deleted and how many tasks there are remaining in the task list.
     * @param task the task that has just been deleted into the task list
     * @param tasks the list of task being managed by Duke
     */
    public static String showDeletedMessage(Task task, TaskList tasks) {
        return "Noted. I've removed this task:\n" + task.toString()
                + "\nNow you have " + tasks.getNumberOfTasks() + " tasks in the list.";
    }

    /**
     * Returns a string to inform the user that the mark is changed.
     * @param task task to be changed
     * @param toMark if the command wishes for the task to be marked or not.
     * @return a string to inform the user that the mark is changed.
     */
    public static String showChangeMarkMessage(Task task, boolean toMark) {
        if (task.toString().equals(noChangeTrigger.toString())) {
            String message = "The task is already ";
            String messageSuffix = toMark ? "marked." : "unmarked.";

            return message + messageSuffix;
        } else {
            String taskDescription = task.toString();
            String messagePrefix = toMark ? "Nice! I've marked this task as done:\n"
                    : "OK, I've marked this task as not done yet:\n";

            return messagePrefix + taskDescription;
        }
    }

    /**
     * Prints out the error message to the user to let the user
     * know why the program cannot run as intended
     * @param errorMessage a message describing the fault
     */
    public static String showErrorMessage(String errorMessage) {
        return "Uh oh... We ran into an error: " + errorMessage;
    }

    /**
     * Returns the list of tasks as a String to be printed out in response to user's request to print the task list.
     * @param tasks list of tasks to be printed
     * @return the list of tasks as a String with Ui features included
     */
    public static String listTasks(TaskList tasks) {
        String listResult = tasks.list();

        if (listResult.equals("")) {
            return "There are currently no elements in the list!";
        } else {
            String listResultPrefix = "These are the tasks currently stored in the list:\n";

            return listResultPrefix + listResult;
        }
    }

    /**
     * Returns a string of tasks containing the keyword.
     * @param tasks list of tasks to be searched
     * @param keyword the keyword that the results have to contain
     * @return the list of matching tasks as a String to be printed
     */
    public static String findTasks(TaskList tasks, String keyword) {
        String findResult = tasks.find(keyword);

        if (findResult.equals("")) {
            return "No results containing the keyword found!";
        } else {
            String findResultPrefix = "Here are the matching tasks in your list:" + System.lineSeparator();

            return findResultPrefix + findResult;
        }
    }

    /**
     * Returns a string informing user that update has been completed.
     * @param task task to be updated
     * @return a string informing user that the task is updated and shows the user the updated task
     */
    public static String showUpdatedMessage(Task task) {
        return "I have updated the task as per your request! This is the updated task:\n" + task.toString();
    }

    /**
     * Returns a string informing user that the cloning action is completed.
     * @param task the cloned task
     * @return a string detailing the cloned task and that cloning is complete.
     */
    public static String showClonedMessage(Task task) {
        return "I have cloned the task and added it to the end of the task list! This is the cloned task:\n"
                + task.toString();
    }
}
