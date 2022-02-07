package aeromon;

import java.util.Scanner;

/**
 * Ui class handles the user interaction with the program.
 */
public class Ui {
    private Scanner sc;

    /**
     * Constructs the UI object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the message.
     * @param message String to be printed.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Reads the command input.
     * @return the command input.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the greeting messages.
     */
    public void greet() {
        System.out.println("Hey, Aeromon here! Fancy a cup of tea?");
    }
}
