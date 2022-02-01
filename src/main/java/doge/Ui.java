package doge;

import doge.command.Command;

import java.util.Scanner;

/**
 * Responsible for the interactions between Doge bot and the user.
 */
public class Ui {

    /**
     * Constructor for class Ui.
     */
    public Ui() {

    }

    /**
     * Prints out the line divider.
     */
    public void showLine() {
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * Prints out the greetings for Doge bot.
     */
    public void greet() {
        System.out.println("Doge: Oh it's you again...");
        System.out.println("Doge: " + "What kind of trouble would you " +
                "inconvenience me with this time?");
    }

    /**
     * Reads the current user input.
     *
     * @return the user input in the form of a String
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Prints out the error message.
     *
     * @param message the error message thrown by an Exception
     */
    public void showError(String message) {
        System.out.println("<ERROR> " + message);
    }

    /**
     * Prints out the response after reading a Command.
     *
     * @param c the Command read
     */
    public void respond(Command c) {
        System.out.println(c);
        if (c.haveTask()) {
            System.out.println(c.getTask());
        }
    }
}
