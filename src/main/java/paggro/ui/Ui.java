package paggro.ui;

import java.util.ArrayList;
import java.util.Scanner;

import paggro.task.Task;

/**
 * This class encapsulates the User Interface of the PaggroBot.
 */
public class Ui {
    /** Scanner used for reading input */
    private Scanner sc;

    /**
     * Constructor of the Ui object.
     */
    public Ui () {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the goodbye message.
     *
     * @return String of the message.
     */
    public String showGoodbye() {
        return "    Oh finally. Please don't come back anytime soon. =.=\n";
    }

    /**
     * Returns a line separator.
     *
     * @return String of line.
     */
    public String showLine() {
        return "   ____________________________________\n";
    }

    /**
     * Returns the given error message.
     *
     * @param msg The error message to be printed.
     * @return String of the message.
     */
    public String showError(String msg) {
        return msg + "\n";
    }

    /**
     * Returns a String of each task in the given list of tasks.
     *
     * @param tasks The given list of tasks.
     * @return String of the tasks.
     */
    public String showList(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "    Nothing to look at here... =.=";
        }
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.isDone()) {
                list.append("    " + Integer.toString(i + 1) + "." + task + "\n");
            } else {
                list.append("    " + Integer.toString(i + 1) + "." + task + "\n");
            }
        }
        return list.toString();
    }

    /**
     * Returns the empty date message.
     *
     * @return String of the message.
     */
    public String showEmptyDate() {
        return "    Nothing happening on this date... =.=\n";
    }

    /**
     * Returns the marked task message.
     *
     * @param task Task that was marked.
     * @return String of the message.
     */
    public String showMarked(Task task) {
        return "    You've finished this task. Good for you... =.=\n      "
                + task + "\n";
    }

    /**
     * Returns the unmarked task message.
     *
     * @param task Task that was unmarked.
     * @return String of the message.
     */
    public String showUnmarked(Task task) {
        return "    Marked undone. Stop slacking off... =.=\n      "
                + task + "\n";
    }

    /**
     * Returns the deleted task message.
     *
     * @param task Task that was deleted.
     * @return String of the message.
     */
    public String showDeleted(Task task) {
        return "    Fine. I've removed this task:\n      "
                + task.toString() + "\n";
    }

    /**
     * Returns the added task message.
     *
     * @param task Task that was added.
     * @return String of the message.
     */
    public String showAdded(Task task) {
        return "    Fine I'll add this task in:\n      "
                + task + "\n";
    }

    /**
     * Returns the given size of task list.
     *
     * @param size Size of the task list.
     * @return String of the message.
     */
    public String showNumber(int size) {
        if (size == 1) {
            return "    Now you have 1 task in the list. =.=\n";
        } else {
            return "    Now you have " + size + " tasks in the list. =.=\n";
        }
    }

}
