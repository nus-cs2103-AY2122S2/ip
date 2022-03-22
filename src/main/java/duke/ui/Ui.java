package duke.ui;

import java.util.List;

import duke.data.TasksEditor;
import duke.data.task.Task;

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
     *
     * @return The response message.
     */
    public String showExit() {
        String msg = "Bye. Hope to see you again soon!\n";
        return msg;
    }

    /**
     * Prints out the error message.
     *
     * @param errorMsg The message to be printed.
     * @return The response message.
     */
    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    /**
     * Lists out the current tasks as well as their status.
     *
     * @param tasksEditor An editor to handle task operations.
     * @return The response message.
     */
    public String list(TasksEditor tasksEditor) {
        String msg = "Here are the tasks in your list:\n" + tasksEditor.toString();
        return msg;
    }

    /**
     * Shows all the tasks which match the searching keywords.
     *
     * @param tasks A list of tasks to be printed.
     * @return The response message.
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
     * @param t A task that has been marked.
     * @return The response message.
     */
    public String mark(Task t) {
        String msg = "Nice! I've marked this task as done:\n   " + t.toString();
        return msg;
    }

    /**
     * Prints the unmarking message.
     *
     * @param t A task that has been unmarked.
     * @return The response message.
     */
    public String unmark(Task t) {
        String msg = "OK, I've marked this task as not done yet:\n   " + t.toString();
        System.out.println(msg);
        return msg;
    }

    /**
     * Prints out the message after a task is added to the list.
     *
     * @param t The task which was just added.
     * @param size The size of the tasklist after removing the above task.
     * @return The response message.
     */
    public String showAddTask(Task t, int size) {
        String msg = "Got it. I've added this task:\n   " + t.toString()
                + "\nNow you have " + size + " tasks in the list.\n";
        return msg;
    }
        
    /**
     * Deletes a task in the specified index.
     *
     * @param t The task to be removed.
     * @param size The size of the task list after removing the above task.
     * @return The response message.
     */
    public String delete(Task t, int size) {
        String msg = "Noted. I've removed this task:\n   " + t.toString()
                + "\nNow you have " + size + " tasks in the list.";
        return msg;
    }

    /**
     * Undoes the latest operation on the task list.
     *
     * @param tasksEditor
     * @param isSuccessful
     * @return
     */
    public String showUndo(TasksEditor tasksEditor, boolean isSuccessful){
        String msg;
        if (isSuccessful) {
            msg = "I've undone the previous operation\n Here are the tasks in your list:\n"
                    + tasksEditor.toString();;
        } else {
            msg = "Nothing to undo.\n";
        }
        return msg;
    }
}
