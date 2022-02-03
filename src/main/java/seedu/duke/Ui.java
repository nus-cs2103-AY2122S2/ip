package seedu.duke;

import seedu.task.Task;
import seedu.task.Todo;

import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLoadingError(String message) {
        System.out.println("File info: " + message);
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLine() {
        System.out.println("-".repeat(12));
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showWelcome() {
        System.out.println("Welcome!");
    }

    public void printDone(Task task, String type) {
        System.out.println(task.toString() + type);
    }
}
