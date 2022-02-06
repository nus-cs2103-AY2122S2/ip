package duke;

import java.util.Scanner;

/**
 * class that takes care of ui
 */
public class Ui {
    private Scanner scanner;
    private String currMessage;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        println("________________________________________________________________");
    }

    /**
     * Method displaying errors
     * @param message the error message
     */
    public void showError(String message) {
        System.out.println(message);
        currMessage += message + "\n";
    }

    /**
     * shows welcome message
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");
    }

    public static String getWelcomeMessage() {
        return "Hello I'm Duke\n" + "What can I do for you?";
    }

    /**
     * method to print the message
     * @param message to be printed
     */
    public void println(String message) {
        currMessage += message + "\n";
        System.out.println(message);
    }
    /**
     * method to print tasks
     * @param task to be printed
     */
    public void println(Task task) {
        System.out.println(task);
        currMessage += task.toString() + "\n";
    }

    public String dumbMessage() {
        return "dumb";
    }
    public String getMessage() {
        String str = currMessage;
        currMessage = "";
        return str;
    }
}
