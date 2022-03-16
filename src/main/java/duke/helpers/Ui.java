package duke.helpers;

import java.util.Scanner;

/**
 * Reads user inputs
 */
public class Ui {

    private static Scanner sc;

    /**
     * Constructs a Ui object and initializes a Scanner object to read user inputs.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message for the user upon running a Duke object
     */
    public void welcome() {
        System.out.println("Hello! I'm duke.Duke\n" + "What can I do for you?");
    }

    /**
     * Reads the next line of user input using a Scanner object
     *
     * @return the next line of user input
     */
    public static String readCommand() {
        return sc.nextLine();
    }

    /**
     * Closes the Scanner object
     */
    public static void closeScanner() {
        sc.close();
    }

    /**
     * Displays an error message for the user if IOException is thrown
     */
    public static void showLoadingError() {
        System.out.println("Loading error!");
    }


}
