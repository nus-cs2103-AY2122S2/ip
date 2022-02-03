package duke.ui;

import duke.task.Task;

import java.util.List;

/**
 * A class that contains methods to print responses to the screen.
 */
public class Ui {
    private String lineBreak = "-------------------------------\n";
    private String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?\n";
    private String goodbyeMessage = "Bye. Hope to see you again soon!";

    /**
     * Returns Ui welcome message.
     *
     * @return Welcome message.
     */
    public String greet() {
        return welcomeMessage;
    }

    /**
     * Returns Ui goodbye message.
     *
     * @return Goodbye message.
     */
    public String goodbye() {
        return goodbyeMessage;
    }

    /**
     * Returns Ui Line divider.
     *
     * @return Line divider.
     */
    public String showLine() {
        return lineBreak;
    }

    /**
     * Returns the successful task added message.
     *
     * @param task Task that was added.
     * @param noOfTasks Number of tasks that are currently in the list of tasks.
     * @return Task added message.
     */
    public String taskAddedMessage(Task task, int noOfTasks) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + noOfTasks + " tasks in the list.";
    }

    /**
     * Returns the contents of a list of tasks.
     *
     * @param tasks List of tasks to be printed.
     * @return String message of the contents of list.
     */
    public String list(List<Task> tasks) {
        String response = String.format("You currently have %d task in your list:\n", tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            response += String.format("%d. %s\n", i + 1, tasks.get(i).toString());
        }
        return response;
    }

    /**
     * Returns message of a successfully marked task.
     *
     * @param task Task that was successfully marked.
     * @return Task marked message.
     */
    public String taskMarkedMessage(Task task) {
        return String.format("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Returns message of a successfully unmarked task.
     *
     * @param task Task that was successfully unmarked.
     * @return Task unmarked message.
     */
    public String taskUnmarkedMessage(Task task) {
        return String.format("OK! I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * Returns message of a successfully deleted task.
     *
     * @param task Task that was successfully deleted.
     * @param noOfTasks Number of Tasks that are currently in the list of tasks.
     * @return Task deleted message.
     */
    public String taskDeleteMessage(Task task, int noOfTasks) {
        return String.format("OK! I've deleted this task:\n" + task.toString()
                + "\nNow you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Returns message to the user to check the number of fields in their input.
     *
     * @return Not enough fields message.
     */
    public String notEnoughFieldsMessage() {
        return "Not enough fields, please check your inputs and try again.";
    }

    /**
     * Prints out message to the user to check the index given in their input.
     *
     * @return Invalid index message.
     */
    public String invalidIndex() {
        return "Index provided is not an integer. Please try again";
    }

    /**
     * Returns message to the user to check the format of the date in their input.
     *
     * @return Invalid date message.
     */
    public String invalidDate() {
        return "Invalid date: Please format date as yyyy-mm-dd";
    }

    /**
     * Returns the contents of a list of task in a String.
     *
     * @return String contents of a list of task.
     */
    public String listFindResults(List<Task> result) {
        String response = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < result.size(); i++) {
            response += String.format("%d. %s\n", i + 1, result.get(i).toString());
        }
        return response;
    }

    /**
     * Returns task not found message.
     *
     * @return Task not found message.
     */
    public String taskNotFound() {
        return "Sorry! There are no matching tasks found.";
    }
}

