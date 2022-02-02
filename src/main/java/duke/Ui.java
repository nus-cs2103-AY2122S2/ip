package duke;

import duke.TaskList;
import duke.task.Task;
import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
      scanner = new Scanner(System.in);
    }

    public String readCommand() {
      return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello from Duke!");
    }

    public void showTaskAdded(Task task) {
        System.out.println(String.format("added: %s", task.toString()));
    }

    public void showTaskDeleted() {
        System.out.println("I've deleted this task.");
    }

    public void showTasks(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    public void showMessage(String str) {
        System.out.println(str);
    }

    public void showExit() {
        System.out.println("Goodbye!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("_____");
    }

    public void showLoadingError() {
        System.out.println("Loading error");
    }

}