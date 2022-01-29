package ann.ui;

import java.util.Scanner;

/**
 * Represents the user interface component which obtains user input
 * and displays the program output to the user.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a message which indicates that the program initialisation has failed.
     */
    public void showFailedInitMessage() {
        System.out.println("Failed to initialize Ann :( Exiting...");
    }

    /**
     * Prints a message to greet the user after the program is set up.
     */
    public void showGreeting() {
        System.out.println("Greetings from Ann");
    }

    /**
     * Prints a message to greet the user after the program is set up.
     */
    public void showExitMessage() {
        System.out.println("Goodbye!");
    }

    /**
     * Returns a String which is the user input.
     *
     * @return a String which is the user input.
     */
    public String getCommand() {
        System.out.println("Please enter a command:");
        return scanner.nextLine();
    }

    /**
     * Prints the specified message to the user.
     *
     * @param message a String which is the message to the user.
     */
    public void showToUser(String message) {
        System.out.println(message);
    }
}