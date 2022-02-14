package doge;

import java.util.Scanner;

import doge.command.Command;

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
        System.out.println("Oh it's you again...");
        System.out.println("What kind of trouble would you "
                + "inconvenience me with this time?");
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
        assert message != null : "error message should not be null!";
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
        assert c != null : "command should not be null";
        StringBuilder output = new StringBuilder();
        output.append(c.toString());
        if (c.haveTask()) {
            output.append("\n").append(c.getTask().toString());
        }
        return output.toString();
    }
}
