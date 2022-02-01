package duke.util;

import java.util.Scanner;

/**
 * Abstracts the user input and Duke print commands.
 */
public class Ui {

    private final Scanner sc;

    /**
     * Constructor for Ui.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints out the welcome message when Duke is first run.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("===============================");
    }

    /**
     * Prints line separator.
     */
    public void showLine() {
        System.out.println("===============================");
    }

    /**
     * Prints any string given.
     * @param str A string that is printed out.
     */
    public void print(String str) {
        System.out.println(str);
    }

    /**
     * Reads the command from user and returns it.
     * @return the next user input.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints exit message.
     */
    public void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        sc.close();
    }

}
