package duke;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private Scanner sc = new Scanner(System.in);

    public Ui() {

    }

    public String[] readCommand() {
        String command = sc.next();
        String details = sc.nextLine().trim();
        return new String[]{command, details};
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What do you need me to note down for you? Type it below!\n" +
                "Feel free to identify the status of your tasks by entering 'marked' or 'unmarked' along with the " +
                "task number!");
        printDoubleLine();
    }

    public void goodbye() {
        printSingleLine();
        System.out.println("Bye. Have a great day!");
        printDoubleLine();
    }

    public void printSingleLine() {
        System.out.println("------------------------------------------------------");
    }

    public void printDoubleLine() {
        System.out.println("======================================================");
    }

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the requested tasks:");
        StringBuilder taskList = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            taskList.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        System.out.println(taskList.toString().trim());
        printDoubleLine();
    }

    public void notifyRemovedTaskMessage(Task t) {
        System.out.println("Noted. I've removed this task:\n" + t);
        printDoubleLine();
    }

    public void notifyAddedTaskMessage(Task t) {
        System.out.println("Noted. I've added this task:\n" + t);
        printDoubleLine();
    }

    public void notifyMarkedTaskMessage(Task t, boolean mark) {
        if (mark) {
            System.out.println("Task " + t + " has been marked complete.");
        } else {
            System.out.println("Task " + t + " has been marked incomplete.");
        }
        printDoubleLine();
    }

}