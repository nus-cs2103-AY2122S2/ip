package Duke;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Printer {
    private static final String border = "    ____________________________________________________________\n";
    private static final String spacing = "    ";
    public static final String logo = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    public void printGreeting() {
        System.out.println("Hello from\n" + logo);

        System.out.println(border + spacing +
                "Hello! I'm Duke\n" + spacing +
                "What can I do for you?\n" +
                border);
    }

    public void printBye() {
        System.out.println(border + spacing + "Bye. Hope to see you again soon!\n" + border);
    }

    public void  printTask(Task task, int numberOfTasks) {
        System.out.println(border
                + spacing
                + "Got it. I've added this task:\n"
                + spacing
                + task.toString()
                + "\n"
                + spacing
                + "Now you have " + numberOfTasks + " tasks in the list.\n"
                + border);
    }

    public void printExceptions(Exception e) {
        System.out.println(border
                + spacing
                + e.getMessage() + "\n"
                + border);
    }

    public void printEmptyList() {
        System.out.println(border
                + spacing
                + "There is currently nothing in your list!\n"
                + border);
    }

    public void printList(ListStorage myStorage) {
        System.out.println(border + spacing
                        + "Here are the tasks in your list:\n"
                        + myStorage.printList()
                        + border);
    }

    public void printUnmark(ListStorage myStorage, int taskNumber) {
        System.out.println(border
                + spacing
                + "OK, I've marked this task as not done yet:\n"
                + myStorage.printTask(taskNumber) + border);
    }

    public void printMark(ListStorage myStorage, int taskNumber) {
        System.out.println(border
                + spacing
                + "Nice! I've marked this task as done:\n"
                + myStorage.printTask(taskNumber) + border);
    }

    public void printDelete(ListStorage myStorage, int taskNumber) {
        System.out.println(border
                + spacing
                + "Noted. I've removed this task:\n"
                + myStorage.printTask(taskNumber)
                + spacing
                + "Now you have " + (myStorage.length() - 1) + " tasks in the list.\n"
                + border);
    }
}
