package duke;

import java.util.Scanner;

import duke.task.Task;



/**
 * Represents a Ui which deals with interactions with the user.
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }
    /**
     * Prints a goodbye message to the user when duke.Duke is shut down.
     */
    public String showByeMessage() {
        return "Ciao! Hope to see you again!";
    }
    /**
     * Shows a String representation of the added task.
     * @param taskList Task list.
     * @return Returns a String message.
     */
    public String showAddedTask(List taskList) {
        return "Got it. I've added this task:\n  "
                + taskList.getLast().toString()
                + "\n" + "Now you have " + taskList.getArrayList().size()
                + " tasks in the list.";
    }

    /**
     * Shows a message when a task is deleted from the list.
     *
     * @param taskList Task List.
     * @param task Task being deleted.
     * @return Returns a String message.
     */
    public String showDeletedTask(List taskList, Task task) {
        return "Roger. I've deleted this task:\n  "
                + task.toString() + "\n"
                + "Now you have "
                + taskList.getArrayList().size()
                + " tasks in the list";
    }

    /**
     * Shows a message when a task in the list is marked done.
     *
     * @param task Task being marked done.
     * @return Returns a String message.
     */
    public String showMarkDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n  "
                + task.toString();
    }

    /**
     * Shows a message when a task is the list is marked as not done.
     *
     * @param task Task being marked as not done.
     * @return Returns a String message.
     */
    public String showUnmarkDoneTask(Task task) {
        return "OK, I've marked this task as not done yet:\n  "
                + task.toString();
    }
    /**
     * Shows the task list.
     *
     * @param taskList Task list.
     * @return Returns a String representation of the task list.
     */
    public String showList(List taskList) {
        return taskList.toString();
    }

    /**
     * Shows a message of the tasks found.
     * @param taskList Task List.
     * @return Returns a String message.
     */
    public String showFindTask(List taskList) {
        String str = "";
        int i = 1;
        for (Task t : taskList.getArrayList()) {
            str += i + ". " + t.toString() + "\n";
            i++;
        }
        return "Here are the matching tasks in your list:\n" + str;
    }
}
