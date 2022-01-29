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
     * Prints a greeting message to greet the user when Duke is run.
     */
    public void greetUser() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "I am at your service.\n");
    }

    /**
     * Prints a goodbye message to the user when Duke is shut down.
     */
    public void byeUser() {
        System.out.println("Ciao! Hope to see you again!");
    }

    public String processCommand() {
        return sc.nextLine();
    }

    public void printAddedTask(List taskList) {
        System.out.println("Got it. I've added this task:\n  "
                + taskList.getLast().toString()
                + "\n" + "Now you have " + taskList.getArrayList().size()
                + " tasks in the list.");
    }

    /**
     * Prints a message when a task is deleted from the list.
     *
     * @param taskList Task List.
     * @param task Task being deleted.
     */
    public void printDeletedTask(List taskList, Task task) {
        System.out.println("Roger. I've deleted this task:\n  "
                + task.toString() + "\n"
                + "Now you have "
                + taskList.getArrayList().size()
                + " tasks in the list");
    }

    /**
     * Prints a message when a task in the list is marked done.
     *
     * @param task Task being marked done.
     */
    public void printMarkDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  "
                + task.toString());
    }

    /**
     * Prints a message when a task is the list is marked as not done.
     *
     * @param task Task being marked as not done.
     */
    public void printUnmarkDoneTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n  "
                + task.toString());
    }

    /**
     * Prints an error message.
     *
     * @param errorMessage Error message to be printed.
     */
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints the task list.
     *
     * @param taskList Task list.
     */
    public void printList(List taskList) {
        System.out.println(taskList);
    }

    public void printFindTask(List taskList) {
        String str = "";
        int i = 1;
        for (Task t : taskList.getArrayList()) {
            str += i + ". " + t.toString() + "\n";
            i++;
        }
        System.out.println("Here are the matching tasks in your list:\n"
                            + str);
    }
}
