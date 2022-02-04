package duke;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents the user interface.
 */
public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    private static String isPlural(int n) {
        if (n < 1 || n > 1) {
            return "s ";
        }
        return " ";
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(tasks);
    }

    public void showFoundTaskList(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks);
    }

    public void showNumberOfTasks(TaskList tasks) {
        System.out.println("You now have " + tasks.size() + " task" + isPlural(tasks.size()) + "in the list.");
    }

    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void showDelete(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    public void showExit(Storage storage, TaskList tasks) throws DukeException {
        sc.close();
        storage.save(tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
