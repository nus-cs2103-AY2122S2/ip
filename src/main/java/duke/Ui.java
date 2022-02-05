package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents a Ui which deals with interactions with the user.
 */
public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }


    /**
     * Prints a greeting message to greet the user when duke.Duke is run.
     */
    public String greetUser() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello I'm\n" + logo + "I am at your service.\n";
    }

    /**
     * Prints a goodbye message to the user when duke.Duke is shut down.
     */
    public String byeUser() {
        return "Ciao! Hope to see you again!";
    }

    public String processCommand() {
        return sc.nextLine();
    }

    public String printAddedTask(List taskList) {
        return "Got it. I've added this task:\n  "
                + taskList.getLast().toString()
                + "\n" + "Now you have " + taskList.getArrayList().size()
                + " tasks in the list.";
    }

    /**
     * Prints a message when a task is deleted from the list.
     *
     * @param taskList Task List.
     * @param task Task being deleted.
     */
    public String printDeletedTask(List taskList, Task task) {
        return "Roger. I've deleted this task:\n  "
                + task.toString() + "\n"
                + "Now you have "
                + taskList.getArrayList().size()
                + " tasks in the list";
    }

    /**
     * Prints a message when a task in the list is marked done.
     *
     * @param task Task being marked done.
     */
    public String printMarkDoneTask(Task task) {
        return "Nice! I've marked this task as done:\n  "
                + task.toString();
    }

    /**
     * Prints a message when a task is the list is marked as not done.
     *
     * @param task Task being marked as not done.
     */
    public String printUnmarkDoneTask(Task task) {
        return "OK, I've marked this task as not done yet:\n  "
                + task.toString();
    }

    /**
     * Prints an error message.
     *
     * @param errorMessage Error message to be printed.
     */
    public String printError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints the task list.
     *
     * @param taskList Task list.
     */
    public String printList(List taskList) {
        return taskList.toString();
    }

    public String printFindTask(List taskList) {
        String str = "";
        int i = 1;
        for (Task t : taskList.getArrayList()) {
            str += i + ". " + t.toString() + "\n";
            i++;
        }
        return "Here are the matching tasks in your list:\n" + str;
    }
}
