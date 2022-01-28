package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * This method greets the user at the start of the program.
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
     * This method bids farewell to the user at the end of the program.
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

    public void printDeletedTask(List taskList, Task task) {
        System.out.println("Roger. I've deleted this task:\n  "
                + task.toString() + "\n"
                + "Now you have "
                + taskList.getArrayList().size()
                + " tasks in the list");
    }

    public void printMarkDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  "
                + task.toString());
    }

    public void printUnmarkDoneTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n  "
                + task.toString());
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

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
