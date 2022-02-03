package seedu.duke;

import java.util.Scanner;

/**
 * Provides methods to interact with user interface and inputs of running chat-bot.
 * Contains methods to print out the welcome and exit lines, and to get the next user input.
 */
public class Ui {

    private Scanner userInput;
    private String nextInput;


    /**
     * Creates new scanner object.
     */
    public Ui() {
        this.userInput = new Scanner(System.in);
    }

    /**
     * Prints out the welcome statement for the Duke chat-bot.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
    }

    /**
     * Reads in the next line of user input to return.
     *
     * @return String containing the next line of user input.
     */
    public String getNextInput() {
         nextInput = userInput.nextLine();
         return nextInput;
    }

    /**
     * Prints out the exit statement for the Duke chat-bot.
     */
    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
