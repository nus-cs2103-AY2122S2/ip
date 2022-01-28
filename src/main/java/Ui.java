package duke;
//Ui: deals with interactions with the user
import java.util.ArrayList;
import java.util.List;

public class Ui {
    Ui() {
    }

    public void start() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void finalBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("System data could not be loaded!");
    }

    public void emptyInput() {
        System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
    }

    public void doNotUnderstand() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void markdone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void removedTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.",tasks.size()));
    }

    public void addTask(TaskList tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));

    }
}
