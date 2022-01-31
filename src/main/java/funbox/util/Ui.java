package funbox.util;

import java.util.ArrayList;
import java.util.Scanner;
import funbox.task.Task;

public class Ui {

    /**
     * Returns a string which greets the user.
     *
     * @return Returns a string which greets the user.
     */
    public String greetUser() {
        return "Yo! I am FunBox [0 _ 0] \nWhat can I do for you?";
    }

    /**
     * Returns a string when list function is called.
     *
     * @return Returns a string when list function is called.
     */
    public String printListHeader() {
        return "Here are the tasks in your list:";
    }

    /**
     * Returns a bye message when bye function is called.
     *
     * @return Returns a bye message when bye function is called.
     */
    public String printBye() {
        return "B-b-bbye. Hope to see you again soon [0 n 0]";
    }

    /**
     * Returns a success message when directory is found.
     *
     * @return Returns a success message when directory is found.
     */
    public String printDirSuccess() {
        return "Directory 'data' has been created for you!";
    }

    /**
     * Returns a message when directory already exist.
     *
     * @return Returns a message when directory already exist.
     */
    public String printDirAlreadyExist() {
       return "Directory already exists!";
    }

    /**
     * Returns a message of an item on the list.
     *
     * @param index The position which the item is listed on the list.
     * @param task The task to be printed.
     * @return Returns a message of an item on the list.
     */
    public String printTask(int index, Task task) {
       return index + "." + task;
    }

    /**
     * Returns an error message.
     *
     * @param errorMessage The error message to be returned.
     * @return Returns an error message.
     */
    public String showError(String errorMessage) {
       return errorMessage;
    }

    /**
     * Returns a header message when a task is being removed.
     *
     * @return Returns a header message when a task is being removed.
     */
    public String printNotice() {
       return "Noted! I've removed this task:";
    }

    /**
     * Returns a message displaying the remaining items on the list.
     *
     * @param taskList The tasklist containing the remaining tasks.
     * @return Returns a message displaying the remaining items on the list.
     */
    public String printRemainingTasks(TaskList taskList) {
       return "Now you have " + taskList.getSize() + " tasks in the list";
    }

    /**
     * Returns a message when no task is found.
     *
     * @return Returns a message when no task is found.s
     */
    public String printNoTaskFound() {
        return "No tasks found on this date! You are free!";
    }

    /**
     * Returns a header message when a task is marked as done.
     *
     * @return Returns a header message when a task is marked as done.
     */
    public String printMarkDone() {
        return "Nice! I've marked this task as done:";
    }

    /**
     * Returns a header message when a task is marked as undone.
     *
     * @return Returns a header message when a task is marked as undone.
     */
    public String printMarkUndone() {
        return "OK, I've marked this task as not done yet:";
    }

    /**
     * Returns a header message when there is no task on the tasklist.
     *
     * @return Returns a header message when there is no task on the tasklist.
     */
    public String emptyList() {
       return "You have no tasks at the moment!";
    }

    /**
     * Returns a message of the deleted task.
     * 
     * @param task The deleted task.
     * @return Returns a message of the deleted task.
     */
    public String printDeletedTask(Task task) {
       return task.toString();
    }
}
