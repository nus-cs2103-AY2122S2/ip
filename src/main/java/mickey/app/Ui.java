package mickey.app;

import mickey.task.Task;

import java.util.Scanner;

public class Ui {
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hey there! I'm Mickey, your Mouse assistant.\nWhat can I do for you today?\n");
    }

    public void showLine() {
        System.out.println("_________________________________________________________________________________________");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void showNewTask(int numTasks, Task t, String type) {
        System.out.println("\tAw, gee! New" + type + ":\n\t\t" + t);
        System.out.println("\tHooray! You now have " + numTasks + " tasks");
    }

    public void showMarkAsDone(Task t) {
        System.out.println("\tThat is swell! You have completed this task:");
        System.out.println("\t\t" + t);
    }

    public void showMarkAsUndone(Task t) {
        System.out.println("\tHot dog! Complete this soon:");
        System.out.println("\t\t" + t);
    }

    public void showDeleteTask(int numTasks, Task t) {
        System.out.println("\tAlrighty. I've removed this task.");
        System.out.println("\t\t" + t);
        System.out.println("\tYou now have " + numTasks + " tasks");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("Failed to load file. Creating new task list.\n");
    }

    public void showBye() {
        System.out.println("\tToodles! See ya real soon!");
    }
}
