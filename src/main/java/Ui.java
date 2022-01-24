import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public enum TypicalString {
        LONG_LINE {
            public String toString() {
                return "____________________________________________________________";
            }
        },
        ADDED_TASK {
            public String toString() {
                return " Got it. I've added this task:";
            }
        },
        HELLO {
            public String toString() {
                return " Hello! I'm Duke";
            }
        },
        GOODBYE {
            public String toString() {
                return " Bye. Hope to see you again soon!";
            }
        }
    }

    public Ui(Scanner sc) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        this.sc = sc;
        startChat();
    }

    public void startChat() {
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(TypicalString.HELLO);
        System.out.println(" What can I do for you?");
        System.out.println(TypicalString.LONG_LINE);
    }

    public void endChat() {
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(TypicalString.GOODBYE);
        System.out.println(TypicalString.LONG_LINE);
    }

    public void showListTask(ArrayList<Task> storingList) {
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 1; i <= storingList.size(); i++) {
            System.out.println(" " + i + "." + storingList.get(i - 1));
        }
        System.out.println(TypicalString.LONG_LINE);
    }

    public void showTaskMark(Task task, boolean isDone) {
        if (isDone) {
            task.taskDone();
            System.out.println(TypicalString.LONG_LINE);
            System.out.println(" Nice! I've marked this task as done: ");
        } else {
            task.taskUndone();
            System.out.println(TypicalString.LONG_LINE);
            System.out.println(" OK, I've marked this task as not done yet: ");
        }
        System.out.println("  " + task);
        System.out.println(TypicalString.LONG_LINE);
    }

    public void showTask(Task task, int numTask) {
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(TypicalString.ADDED_TASK);
        System.out.println("  " + task);
        System.out.println(" Now you have " + numTask + " tasks in the list.");
        System.out.println(TypicalString.LONG_LINE);
    }

    public void showDeleteTask(Task task, int numTask) {
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("  " + task);
        System.out.println(" Now you have " + numTask + " tasks in the list.");
        System.out.println(TypicalString.LONG_LINE);
    }

    public void showDate(ArrayList<Task> eventList) {
        System.out.println(TypicalString.LONG_LINE);
        System.out.println("You have " + eventList.size() +
                " deadlines/events in the day:");
        for (int i = 1; i <= eventList.size(); i++) {
            System.out.println(i + "." + eventList.get(i - 1));
        }
        System.out.println(TypicalString.LONG_LINE);
    }

    public void showLoadingError() {
        System.out.println(TypicalString.LONG_LINE);
        System.out.println(" Sorry, some problem have occurred during initialization: ");
        System.out.println(TypicalString.LONG_LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
