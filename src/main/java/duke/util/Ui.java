package duke.util;

import duke.command.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    FastIO sc;

    // Constructor
    public Ui() {
        this.sc = new FastIO();
    }


    // Reads user input commands
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }


    // Welcome and Bye
    public static void showWelcome() {
        System.out.println("Hello there, I'm Duke! Let's chat!");
    }

    public void bye() {
        System.out.println("Bye! It was nice having you!");
        sc.close();
    }


    // List out tasks
    public static void list(TaskList list) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
    }


    // Prints exceptions
    public void printException(DukeException e) {
        System.out.println("ERROR: " + e.getMessage());
    }


    // Mark, Unmark, Delete
    public static void mark(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task.toString());
    }

    public static void unmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet: \n" + task.toString());
    }

    public static void delete(Task task, int size) {
        System.out.println("Noted. I've removed this task: \n" + task.toString() + "\nNow you have " + size + " tasks in the list.");
    }


    // Add task
    public static void add(Task task, int size) {
        System.out.println("Got it. I've added this task: \n" + task.toString() + "\nNow you have " + size + " tasks in the list.");
    }

}
