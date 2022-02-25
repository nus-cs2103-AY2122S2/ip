package duke.ui;

import java.util.Scanner;

/**
 * Encapsulates Duke's UI interactions between user and client.
 */
public class Ui {

    /**
     * Constant defining indent for program outputs
     */
    private static final String INDENT = "     ";
    /**
     * Constant defining horizontal line printed
     * before and after program outputs
     */
    private static final String LINE = "    ____________________________________________________________";

    /**
     * Prints a horizontal line
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Sandwiches a program output between 2 horizontal lines
     *
     * @param str the str
     */
    public void sandwichMessage(String str) {
        showLine();
        System.out.println(INDENT+str);
        showLine();
        System.out.println();
    }

    /**
     * Reads in user input
     *
     * @return User input string
     */
    public String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints an indented program output
     *
     * @param message Program output to be indented
     */
    public void showMessage(String message) {
        System.out.println(INDENT + message);
    }

    /**
     * Prints welcome message
     */
    public void showWelcomeMessage() {
        sandwichMessage("Hello! I'm Duke\n"
                + INDENT + "What can I do for you?");
    }

    /**
     * Prints farewell message
     */
    public void showFarewellMessage() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Prints error message when loading tasks
     * from local disk to program
     */
    public void showLoadingError() {
        showMessage("Whoops! Error loading tasks.");
        showMessage("Try clearing tasks.txt and trying again?");
        showMessage("xoxo");
    }

    /**
     * Prints error message
     *
     * @param errorMessage error message
     */
    public void showErrorMessage(String errorMessage) {
        showMessage("ERROR: " + errorMessage);
    }
}
