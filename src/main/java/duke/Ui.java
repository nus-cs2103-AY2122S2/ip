package duke;

import java.util.Scanner;

/**
 * Represents the user interface
 */
public class Ui {
    private Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Greets user as they launch the application
     */
    void greet() {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
    }

    /**
     * Prints response to the standard output
     * @param response Response that is to be printed
     */
    void print(String response) {
        System.out.println(response);
    }

    /**
     * Reads user input
     * @return String representing the user input
     */
    String readCommand() {
        return this.scanner.nextLine();
    }
}
