package spark;

import spark.exceptions.SparkException;

import java.util.Scanner;

/**
 * Encapsulates all methods and functions required to
 * implement a Command-Line Interface allowing the user
 * to send inputs and receive feedback from Spark.Spark.
 */
public class Ui {
    private static final String normalLineSeparator = "======================================================================";
    private static final String exceptionLineSeparator = "======================================================================";
    private static final String defaultWelcomeMessage = String.format("%s\n%s",
            "Hello I'm Spark!",
            "What can I do for you?"
            );

    private final Scanner userInput;

    public Ui() {
        this.userInput = new Scanner(System.in);
    }

    /**
     * Gets the next input from the user.
     *
     * @return What the user has typed in the next line.
     */
    public String getInput() {
        System.out.println("What would you like to do next?");
        System.out.print("> ");

        return userInput.nextLine();
    }

    /**
     * On initialization of Spark.Spark, display a welcome message
     * to the user.
     */
    public void printWelcomeMessage() {
        printMessageWithDivider(defaultWelcomeMessage);
    }

    /**
     * Prints a message surrounded by line-separators.
     *
     * @param message
     */
    public void printMessageWithDivider(String message) {
        System.out.println(normalLineSeparator);
        System.out.println(message);
        System.out.println(normalLineSeparator);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a SparkException surrounded by line-separators.
     *
     * @param e
     */
    public void printException(SparkException e) {
        System.out.println(exceptionLineSeparator);
        System.out.println(String.format("%s %s", "[ERROR]", e.getMessage()));
        System.out.println(exceptionLineSeparator);
    }
}
