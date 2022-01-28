package duke.ui;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with interactions with the user
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints exit message
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints list of tasks in the given format
     *
     * @param tasks list of tasks
     */
    public void showList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, tasks.get(i)));
        }
    }

    /**
     * Prints task marked as done
     *
     * @param task task marked as done
     */
    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints task marked as undone
     *
     * @param task task marked as undone
     */
    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints task added to list of tasks
     *
     * @param task task added to list of tasks
     * @param tasks current list of tasks
     */
    public void showAddTask(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (tasks.size() <= 1) {
            System.out.println(String.format("Now you have %d task in the list.", tasks.size()));
        }
        else {
            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        }
    }

    /**
     * Prints task deleted from list of tasks
     *
     * @param task task deleted from list of tasks
     * @param tasks current list of tasks
     */
    public void showDeleteTask(Task task, ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    /**
     * Prints list of matching tasks in the given format
     *
     * @param tasks list of matching tasks
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, tasks.get(i)));
        }
    }

    /**
     * Prints line
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints loading error message
     *
     * @param message loading error message
     */
    public void showLoadingError(String message) {
        System.out.println(message);
    }

    /**
     * Prints error message
     *
     * @param message error message
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Reads user input and returns it
     *
     * @return user input
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
