import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    protected Scanner scan;

    public Ui() {
        scan = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d.%s", i + 1, tasks.get(i)));
        }
    }

    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showAddTask(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        if (tasks.size() <= 1) {
            System.out.println(String.format("Now you have %d task in the list.", tasks.size()));
        }
        else {
            System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
        }
    }
    public void showDeleteTask(Task task, ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scan.nextLine();
    }
}
