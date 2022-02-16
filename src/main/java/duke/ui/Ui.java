package duke.ui;

import java.util.List;

import duke.data.task.Task;
import duke.data.TaskList;

public class Ui {

    public Ui() {
    }

    /**
     * Prints the loading error message.
     */
    public void showLoadingError() {
        System.out.println("\t An error occurs when loading data");
    }

    /**
     * Prints out the goodbye words.
     */
    public String showExit() {
        String msg = "Bye. Hope to see you again soon!\n";
        return msg;
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
     * Lists out the current tasks as well as their status.
     *
     * @param tasks a list of all tasks
     */
    public String list(TaskList tasks) {
        String msg = "Here are the tasks in your list:\n" + tasks.toString();
        return msg;
    }

    /**
     * Shows all the tasks which match the searching keywords.
     *
     * @param tasks a list of tasks to be printed
     */
    public String find(List<Task> tasks) {
        String msg = "Here are the matching tasks in your list:";
        for (Task t : tasks) {
            msg += t.toString() + "\n";
        }
        return msg;
    }

    /**
     * Prints the marking message.
     *
     * @param t a task that has been marked.
     */
    public String mark(Task t) {
        String msg = "Nice! I've marked this task as done:\n   " + t.toString();
        return msg;
    }

    /**
     * Prints the unmarking message.
     *
     * @param t a task that has been unmarked.
     */
    public String unmark(Task t) {
        String msg = "OK, I've marked this task as not done yet:\n   " + t.toString();
        System.out.println(msg);
        return msg;
    }

    /**
     * Prints out the message after a task is added to the list.
     *
     * @param t the task which was just added.
     * @param size the size of the tasklist after removing the above task.
     */
    public String showAddTask(Task t, int size) {
        String msg = "Got it. I've added this task:\n   " + t.toString()
                + "\nNow you have " + size + " tasks in the list.\n";
        return msg;
    }
        
    /**
     * Deletes a task in the specified index.
     *
     * @param t the task to be removed.
     * @param size the size of the task list after removing the above task.
     */
    public String delete(Task t, int size) {
        String msg = "Noted. I've removed this task:\n   " + t.toString()
                + "\nNow you have " + size + " tasks in the list.";
        return msg;
    }
}
