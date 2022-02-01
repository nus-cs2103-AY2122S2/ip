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
    public String greet() {
        System.out.println("Doge: Oh it's you again...");
        System.out.println("Doge: " + "What kind of trouble would you " +
                "inconvenience me with this time?");
        return "Oh it's you again...\nWhat kind of trouble would you inconvenience me with this time?";
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
     * @return the error message as a String
     */
    public String showError(String message) {
        String output = "<ERROR> " + message;
        System.out.println(output);
        return output;
    }

    /**
     * Returns the response as a String after reading a Command.
     *
     * @param c the Command read
     * @return Doge's response as a String
     */
    public String respond(Command c) {
        StringBuilder output = new StringBuilder();
        output.append(c.toString());
        if (c.haveTask()) {
            output.append("\n").append(c.getTask().toString());
        }
        return output.toString();
    }
}
