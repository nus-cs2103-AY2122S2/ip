package duke;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user interface.
 */
public class Ui {
    private final Scanner sc;
    /**
     * Constructs a Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Appends an 's' to the back of a word if it is plural.
     */
    private static String pluralise(int n) {
        if (n < 1 || n > 1) {
            return "s ";
        }
        return " ";
    }

    /**
     * Prints a welcome message.
     */
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

    /**
     * Prints the error.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Prints the tasks.
     * @param tasks The tasks.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.size() == 0) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(tasks);
        }
    }

    /**
     * Prints the tasks that match the keyword.
     * @param tasks The tasks the match the keyword.
     */
    public void showFoundTaskList(TaskList tasks) {
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(tasks);
    }

    /**
     * Prints the number of tasks.
     * @param tasks The tasks.
     */
    public void showNumberOfTasks(TaskList tasks) {
        System.out.println("You now have " + tasks.size() + " task" + pluralise(tasks.size()) + "in the list.");
    }

    /**
     * Prints the task added.
     * @param task The task added.
     */
    public void showTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    /**
     * Prints the marked task.
     * @param task The marked task.
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the unmarked task.
     * @param task The unmarked task.
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints the deleted task.
     * @param task The deleted task.
     */
    public void showDelete(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Saves tasks to storage and prints a farewell message.
     * @param storage The storage.
     * @param tasks The tasks to be saved.
     */
    public void showExit(Storage storage, TaskList tasks) throws DukeException {
        sc.close();
        storage.save(tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the incoming command.
     * @return The incoming command.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
