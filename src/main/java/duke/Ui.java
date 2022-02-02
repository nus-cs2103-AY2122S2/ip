package duke;

import duke.TaskList;
import duke.task.Task;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads line from command line input.
     *
     * @return Line from command line.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays welcome message when user starts app.
     */
    public void showWelcome() {
        System.out.println("Hello from Duke!");
    }

    /**
     * Shows display when a task is added.
     */
    public void showTaskAdded(Task task) {
        System.out.println(String.format("added: %s", task.toString()));
    }

    /**
     * Shows display when a task is deleted.
     */
    public void showTaskDeleted() {
        System.out.println("I've deleted this task.");
    }

    /**
     * Shows list of all tasks in specified list
     *
     @param tasks List of all tasks.
     */
    public void showTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    /**
     * Shows display when user exits app.
     */
    public void showExit() {
        System.out.println("Goodbye!");
    }

    /**
     * Displays specified error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Shows divider.
     */
    public void showLine() {
        System.out.println("_____");
    }

    /**
     * Shows loading error.
     */
    public void showLoadingError() {
        System.out.println("Loading error");
    }

}