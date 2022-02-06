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
    public String showNewTask(int numTasks, Task t, String type) {
        return "Aw, gee! New " + type + ":\n\t" + t.toString() + "\nHooray! You now have " + numTasks + " tasks";
    }

    /**
     * Print mark task as done.
     *
     * @param t Task to print.
     */
    public String showMarkAsDone(Task t) {
        return "That is swell! You have completed this task:\n" + t;
    }

    /**
     * Print mark task as undone.
     *
     * @param t Task to print.
     */
    public String showMarkAsUndone(Task t) {
        return "Hot dog! Complete this soon:\n" + t;
    }

    /**
     * Print delete task message.
     *
     * @param numTasks Number of current tasks.
     * @param t Task to print.
     */
    public String showDeleteTask(int numTasks, Task t) {
        return "Alrighty. I've removed this task.\n" + t + "You now have " + numTasks + " tasks";
    }

    /**
     * Print error message.
     *
     * @param msg Message string.
     */
    public String showError(String msg) {
        return msg;
    }

    /**
     * Print load file failed error.
     */
    public String showLoadingError() {
        return "Failed to load file. Creating new task list.";
    }

    /**
     * Print exit message.
     */
    public String showBye() {
        return "Toodles! See ya real soon!";
    }
}
