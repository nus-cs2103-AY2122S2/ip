package duke.ui;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greet);
    }

    public String[] readCommand() {
        String[] commandList = new String[]{sc.next(),sc.nextLine()};
        return commandList;
    }

    public void showLine() {
        System.out.println("----------------------------------");
    }

    public void showLoadingError() {
        System.out.println("Opps! There is loading error :<");
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void showGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showTaskAdded(Task task, ArrayList<Task> taskList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, ArrayList<Task> taskList) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task + "\n" + "Now you have " + taskList.size()
                + " tasks in the list.");
    }

    /**
     * To display the list of tasks.
     */
    public void showList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("No task for now");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < taskList.size() + 1; i++) {
                Task currTask = taskList.get(i - 1);
                System.out.println(i + ". " + currTask);
            }
        }
    }

    public void showTaskMarked(Task currTask) {
        System.out.println("Nice! I've marked this task as done:\n"
                + currTask);
    }

    public void showTaskUnmarked(Task currTask) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + currTask);
    }



}
