package paggro.ui;

import java.util.ArrayList;
import java.util.Scanner;

import paggro.task.Task;

/**
 * This class encapsulates the User Interface of the PaggroBot.
 */
public class Ui {
    /** Scanner used for reading input */
    Scanner sc;

    /**
     * Constructor of the Ui object.
     */
    public Ui () {
        sc = new Scanner(System.in);
    }

    /**
     * Prints out welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("    Hi I'm PaggroBot =.=\n    What do you want? =.=");
        showLine();
    }

    /**
     * Prints out goodbye message.
     */
    public void showGoodbye() {
        System.out.println("    Oh finally. Please don't come back anytime soon. =.=");
    }

    /**
     * Prints out a line separator.
     */
    public void showLine() {
        System.out.println("   ________________________________________");
    }

    /**
     * Returns the user input as a String.
     * @return String of user input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out given error message.
     * @param msg The error message to be printed.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    /**
     * Prints out each task in the given list of tasks.
     * @param tasks The given list of tasks.
     */
    public void showList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("    Nothing to look at here... =.=");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isDone) {
                System.out.println("    " + Integer.toString(i + 1) + "." + task);
            } else {
                System.out.println("    " + Integer.toString(i + 1) + "." + task);
            }
        }
    }

    /**
     * Prints out empty date message.
     */
    public void showEmptyDate() {
        System.out.println("    Nothing happening on this date... =.=");
    }

    /**
     * Prints out marked task message.
     * @param task Task that was marked.
     */
    public void showMarked(Task task) {
        System.out.println("    You've finished this task. Good for you... =.=\n      " + task);
    }

    /**
     * Prints out unmarked task message.
     * @param task Task that was unmarked.
     */
    public void showUnmarked(Task task) {
        System.out.println("    Marked undone. Stop slacking off... =.=\n      " + task);
    }

    /**
     * Prints out deleted task message.
     * @param task Task that was deleted.
     */
    public void showDeleted(Task task) {
        System.out.println("    Fine. I've removed this task:\n      " + task.toString());
    }

    /**
     * Prints out added task message.
     * @param task Task that was added
     */
    public void showAdded(Task task) {
        System.out.println("    Fine I'll add this task in:\n      " + task);
    }

    /**
     * Prints out given size of task list.
     * @param size Size of the task list.
     */
    public void showNumber(int size) {
        if (size == 1) {
            System.out.println("    Now you have 1 task in the list. =.=");
        } else {
            System.out.println("    Now you have " + size + " tasks in the list. =.=");
        }
    }

}
