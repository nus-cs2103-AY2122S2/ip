package duke.ui;

import java.util.List;
import java.util.Scanner;

import duke.data.task.Task;
import duke.data.TaskList;

public class Ui {

    private static final String DIVIDER = "\t____________________________________________________________\n";

    private final Scanner in;

    /** Shows the greeting message when creating a new Ui object */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Read a line of command.
     *
     * @return the command line
     */
    public String readCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        System.out.println("\t An error occurs when loading data");
    }

    /**
     * Prints the divider.
     */
    public void showLine() {
        System.out.print(Ui.DIVIDER);
    }

    /**
     * Prints out the greeting words.
     */
    public void showWelcome() {
        this.printMsg("\t Hello! I'm Duke\n\t What can I do for you?\n");
    }

    /**
     * Prints out the goodbye words.
     */
    public void showExit() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    /**
     * Prints out the error message.
     *
     * @param errorMsg the message to be printed
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints the msg between line breaks.
     *
     * @param msg A string of message to be printed
     */
    private void printMsg(String msg) {
        System.out.println(Ui.DIVIDER + msg + Ui.DIVIDER);
    }

// ====================================================

    /**
     * Lists out the current tasks as well as their status.
     *
     * @param tasks a list of all tasks
     */
    public void list(TaskList tasks) {
        String msg = tasks.toString();
        System.out.print("\t Here are the tasks in your list:\n" + msg);
    }

    /**
     * Shows all the tasks which match the searching keywords.
     *
     * @param tasks a list of tasks to be printed
     */
    public void find(List<Task> tasks) {
        System.out.println("\t Here are the matching tasks in your list:");
        for (Task t : tasks) {
            System.out.println("\t " + t.toString());
        }
    }

    /**
     * Prints the marking message.
     *
     * @param t a task that has been marked.
     */
    public void mark(Task t) {
        System.out.println("\t Nice! I've marked this task as done:\n\t   " + t.toString());
    }

    /**
     * Prints the unmarking message.
     *
     * @param t a task that has been unmarked.
     */
    public void unmark(Task t) {
        System.out.println("\t OK, I've marked this task as not done yet:\n\t   " + t.toString());
    }

    /**
     * Prints out the message after a task is added to the list.
     *
     * @param t the task which was just added.
     * @param size the size of the tasklist after removing the above task.
     */
    public void showAddTask(Task t, int size) {
        System.out.println("\t Got it. I've added this task:\n\t   " + t.toString()
                + "\n\t Now you have " + size + " tasks in the list.");
    }
        
    /**
     * Deletes a task in the specified index.
     *
     * @param t the task to be removed.
     * @param size the size of the task list after removing the above task.
     */
    public void delete(Task t, int size) {
        System.out.println("\t Noted. I've removed this task:\n\t   " + t.toString()
                + "\n\t Now you have " + size + " tasks in the list.");
    }
}
