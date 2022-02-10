package duke.ui;

import duke.task.Task;
import duke.tasklist.DukeList;

public class Ui {

    private final String Greeting = "\nDuke: Hello! I'm Duke\n      What can I do for you?\n";
    private final String Closing = "\nDuke:  Bye. Hope to see you again soon!";

    /**
     * Prints greeting message to the console.
     */
    public String showWelcome() {
        return Greeting;
    }

    /**
     * Prints closing message to the console.
     */
    public String showClosing() {
        return Closing;
    }

    /**
     * Prints the list of Tasks to the console.
     * @param a DukeList to be printed
     * @return
     */
    public String printList(DukeList a) {
        String msg;
        msg = "\nDuke:\nHere are the tasks in your list:\n";
        msg += a.toString();
        msg += "\n";
        return msg;
    }


    public String markTask(Task t) {
        return "Duke: Nice! I've marked this task as done:\n      " + t;
    }

    public String unmarkTask(Task t) {
        return "Duke: OK, I've marked this task as not done yet:\n      " + t;
    }

    /**
     * Prints out message for adding Task to the list
     * @param t Task that was added
     * @param n Current size of list
     */
    public String addTask(Task t, int n) {
        assert t != null : "Task that is added cannot be null";
        String msg = "\nDuke: Got it. I've added this task:\n      "
                + t + "\n      Now you have "
                + n + " tasks in the list.\n";
        return msg;
    }

    /**
     * Prints out message for deleting Task from the list. The number of tasks left is after deletion.
     * @param t
     * @param n
     * @return
     */
    public String deleteTask(Task t, int n) {
        assert t != null : "Task to be deleted cannot be null";
        int numleft = n - 1;
        String msg = "\nDuke: Noted. I've removed this task:\n      "
                + t + "\n      Now you have "
                + numleft + " tasks in the list.\n";
        return msg;
    }

}
