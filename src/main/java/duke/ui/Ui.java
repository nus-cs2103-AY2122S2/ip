package duke.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;

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

    /**
     * Reads input from the console.
     * @return String of input from console
     */
    public String readInput() {
        Scanner s = new Scanner(System.in);
        String ans = "";
        try {
            ans = s.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Input a command!");
        }
        return ans;
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
        String msg = "\nDuke: Got it. I've added this task:\n      "
                + t + "\n      Now you have "
                + n + " tasks in the list.\n";
        return msg;
    }

    /**
     * Prints out message for deleting Task from the list
     * @param t
     * @param n
     * @return
     */
    public String deleteTask(Task t, int n) {
        String msg = "\nDuke: Noted. I've removed this task:\n      "
                + t + "\n      Now you have "
                + n + " tasks in the list.\n";
        return msg;
    }

}
