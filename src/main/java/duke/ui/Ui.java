package duke.ui;

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

    public String readCommand() {
        String fullInputLine = in.nextLine();
        return fullInputLine;
    }

    public void showLoadingError() {
        System.out.println("\t An error occurs when loading data");
    }

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

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Prints the msg between line breaks.
     *
     * @param msg A string of message to be printed.
     */
    private void printMsg(String msg) {
        System.out.println(Ui.DIVIDER + msg + Ui.DIVIDER);
    }

// ====================================================

    /**
     * Lists out the current tasks as well as their status.
     *
     * @param tasks A list of all tasks.
     */
    public void list(TaskList tasks) {
        String msg = tasks.toString();
        System.out.print("\t Here are the tasks in your list:\n" + msg);
    }

    /**
     * Prints the marking message.
     *
     * @param t A task that has been marked.
     */
    public void mark(Task t) {
        System.out.println("\t Nice! I've marked this task as done:\n\t   " + t.toString());
    }

    /**
     * Prints the unmarking message.
     *
     * @param t A task that has been unmarked.
     */
    public void unmark(Task t) {
        System.out.println("\t OK, I've marked this task as not done yet:\n\t   " + t.toString());
    }

    /**
     * Prints out the message after a task is added to the list.
     *
     * @param t The task which was just added.
     * @param size The size of the tasklist after removing the above task.
     */
    public void showAddTask(Task t, int size) {
        System.out.println("\t Got it. I've added this task:\n\t   " + t.toString()
                + "\n\t Now you have " + size + " tasks in the list.");
    }
        
    /**
     * Deletes a task in the specified index.
     *
     * @param t The task to be removed.
     * @param size The size of the tasklist after removing the above task.
     */
    public void delete(Task t, int size) {
        System.out.println("\t Noted. I've removed this task:\n\t   " + t.toString()
                + "\n\t Now you have " + size + " tasks in the list.");
    }
}
