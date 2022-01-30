package duke.main;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private String output;

    public Ui() {
        this.output = "";
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        output = "Hello! I'm Duke\nWhat can I do for you?";
    }

    /**
     * Prints exit message
     */
    public void showExit() {
       output = "Bye. Hope to see you again soon!";
    }

    /**
     * Prints list of tasks in the given format
     *
     * @param tasks list of tasks
     */
    public void showList(ArrayList<Task> tasks) {
        output = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Prints task marked as done
     *
     * @param task task marked as done
     */
    public void showMarkTask(Task task) {
        output = "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Prints task marked as undone
     *
     * @param task task marked as undone
     */
    public void showUnmarkTask(Task task) {
        output = "OK, I've marked this task as not done yet:\n" + task + "\n";
    }

    /**
     * Prints task added to list of tasks
     *
     * @param task task added to list of tasks
     * @param tasks current list of tasks
     */
    public void showAddTask(Task task, ArrayList<Task> tasks) {
        output = "Got it. I've added this task:\n" + task + "\n";
        if (tasks.size() <= 1) {
            output += String.format("Now you have %d task in the list.\n", tasks.size());
        }
        else {
            output += String.format("Now you have %d tasks in the list.\n", tasks.size());
        }
    }

    /**
     * Prints task deleted from list of tasks
     *
     * @param task task deleted from list of tasks
     * @param tasks current list of tasks
     */
    public void showDeleteTask(Task task, ArrayList<Task> tasks) {
        output = "Noted. I've removed this task:\n" + task + "\n" +
                String.format("Now you have %d tasks in the list.\n", tasks.size());
    }

    /**
     * Prints list of matching tasks in the given format
     *
     * @param tasks list of matching tasks
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        output = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            output += String.format("%d.%s\n", i + 1, tasks.get(i));
        }
    }

    /**
     * Prints loading error message
     *
     * @param message loading error message
     */
    public void showLoadingError(String message) {
        output = message;
    }

    /**
     * Prints error message
     *
     * @param message error message
     */
    public void showError(String message) {
        output = message;
    }

    /**
     * Reads user input and returns it
     *
     * @return user input
     */
  //  public String readCommand() {
    //    return sc.nextLine();
    //}
    public String toString() {
        return output;
    }
}
