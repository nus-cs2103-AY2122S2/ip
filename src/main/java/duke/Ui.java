package duke;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactions with the user through input and output.
 */
public class Ui {

    private Scanner sc = new Scanner(System.in);

    /**
     * Ui constructor.
     */
    public Ui() {
    }

    /**
     * Take in the next line of input from the user and split it into Strings of first word and all other words.
     * @return an array of the two strings
     */
    public String[] readCommand() {
        String command = sc.next();
        String details = sc.nextLine().trim();
        return new String[]{command, details};
    }

    /**
     * Greets the user as the app starts up.
     */
    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What do you need me to note down for you? Type it below!\n" +
                "Feel free to identify the status of your tasks by entering 'marked' or 'unmarked' along with the " +
                "task number!");
        printDoubleLine();
    }

    /**
     * Says goodbye to the user as the app shuts down.
     */
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

    /**
     * Prints out a given list of tasks for the user to see.
     * @param tasks arraylist of desired tasks to show the user.
     */
    public void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the requested tasks:");
        StringBuilder taskList = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            taskList.append(i).append(". ").append(tasks.get(i - 1)).append("\n");
        }
        System.out.println(taskList.toString().trim());
        printDoubleLine();
    }

    /**
     * used when a task has been successfully deleted.
     * @param t task that was removed.
     */
    public void notifyRemovedTaskMessage(Task t) {
        System.out.println("Noted. I've removed this task:\n" + t);
        printDoubleLine();
    }

    /**
     * used when a task has been successfully added.
     * @param t task that was added.
     */
    public void notifyAddedTaskMessage(Task t) {
        System.out.println("Noted. I've added this task:\n" + t);
        printDoubleLine();
    }

    /**
     * used wehn a task was successully marked as incomplete or complete.
     * @param t task that was marked
     * @param isComplete boolean flag for wehether the task was marked complete or not complete.
     */
    public void notifyMarkedTaskMessage(Task t, boolean isComplete) {
        if (isComplete) {
            System.out.println("Task " + t + " has been marked complete.");
        } else {
            System.out.println("Task " + t + " has been marked incomplete.");
        }
        printDoubleLine();
    }

}