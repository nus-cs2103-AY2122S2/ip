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
     * Prints welcome message to the screen.
     */
    public void greet() {
        System.out.println(lineBreak + welcomeMessage + lineBreak);
    }

    /**
     * Prints goodbye message to the screen.
     */
    public void goodbye() {
        System.out.println(goodbyeMessage);
    }

    /**
     * Prints line divider to the screen.
     */
    public void showLine() {
        System.out.println(lineBreak);
    }

    /**
     * Prints the successful task added message to the screen.
     *
     * @param task Task that was added.
     * @param noOfTasks Number of tasks that are currently in the list of tasks.
     */
    public void taskAddedMessage(Task task, int noOfTasks) {
        System.out.println("Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Prints out the contents of a list of tasks.
     *
     * @param tasks List of tasks to be printed.
     */
    public void list(List<Task> tasks) {
        System.out.printf("You currently have %d task in your list:\n", tasks.size());
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i).toString());
        }
    }

    /**
     * Prints out message of a successfully marked task.
     *
     * @param task Task that was successfully marked.
     */
    public void taskMarkedMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString());
    }

    /**
     * Prints out message of a successfully unmarked task.
     *
     * @param task Task that was successfully unmarked.
     */
    public void taskUnmarkedMessage(Task task) {
        System.out.println("OK! I've marked this task as not done yet:\n" + task.toString());
    }

    /**
     * Prints out message of a successfully deleted task.
     *
     * @param task Task that was successfully deleted.
     * @param noOfTasks Number of Tasks that are currently in the list of tasks.
     */
    public void taskDeleteMessage(Task task, int noOfTasks) {
        System.out.println("OK! I've deleted this task:\n" + task.toString()
                + "\nNow you have " + noOfTasks + " tasks in the list.");
    }

    /**
     * Prints out message to the user to check the number of fields in their input.
     */
    public void notEnoughFieldsMessage() {
        showLine();
        System.out.println("Not enough fields, please check your inputs and try again.");
        showLine();
    }

    /**
     * Prints out message to the user to check the index given in their input.
     */
    public void invalidIndex() {
        showLine();
        System.out.println("Index provided is not an integer. Please try again");
        showLine();
    }

    /**
     * Prints out message to the user to check the format of the date in their input.
     */
    public void invalidDate() {
        showLine();
        System.out.println("Invalid date: Please format date as yyyy-mm-dd");
        showLine();
    }
}

