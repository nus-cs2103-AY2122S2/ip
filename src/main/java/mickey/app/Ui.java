package mickey.app;

import mickey.task.Task;

import java.util.Scanner;

/**
 * Ui to print feedback based on user input.
 */
public class Ui {
    /** Scanner object. */
    Scanner sc;

    /**
     * Constructor.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Print welcome message.
     */
    public void showWelcome() {
        System.out.println("Hey there! I'm Mickey, your Mouse assistant.\nWhat can I do for you today?\n");
    }

    /**
     * Print line.
     */
    public void showLine() {
        System.out.println("____________________________________________________________________________");
    }

    /**
     * Reads user input line.
     *
     * @return String containing user input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Print new task message.
     *
     * @param numTasks Number of current tasks.
     * @param t Task to print.
     * @param type Type of task.
     */
    public void showNewTask(int numTasks, Task t, String type) {
        System.out.println("\tAw, gee! New " + type + ":\n\t\t" + t);
        System.out.println("\tHooray! You now have " + numTasks + " tasks");
    }

    /**
     * Print mark task as done.
     *
     * @param t Task to print.
     */
    public void showMarkAsDone(Task t) {
        System.out.println("\tThat is swell! You have completed this task:");
        System.out.println("\t\t" + t);
    }

    /**
     * Print mark task as undone.
     *
     * @param t Task to print.
     */
    public void showMarkAsUndone(Task t) {
        System.out.println("\tHot dog! Complete this soon:");
        System.out.println("\t\t" + t);
    }

    /**
     * Print delete task message.
     *
     * @param numTasks Number of current tasks.
     * @param t Task to print.
     */
    public void showDeleteTask(int numTasks, Task t) {
        System.out.println("\tAlrighty. I've removed this task.");
        System.out.println("\t\t" + t);
        System.out.println("\tYou now have " + numTasks + " tasks");
    }

    /**
     * Print error message.
     *
     * @param msg Message string.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Print load file failed error.
     */
    public void showLoadingError() {
        System.out.println("Failed to load file. Creating new task list.\n");
    }

    /**
     * Print exit message.
     */
    public void showBye() {
        System.out.println("\tToodles! See ya real soon!");
    }
}
