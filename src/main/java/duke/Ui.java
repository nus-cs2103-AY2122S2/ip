package duke;

import java.util.Scanner;

/**
 * The Ui class deals with the interactions with the user via text.
 */
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Displays the error message of the bot application.
     *
     * @param errorMsg the error message to be displayed.
     */
    public void showError(String errorMsg) {
        printMsg(errorMsg);
    }

    /**
     * Displays the welcome message of the bot application.
     */
    public void showWelcome() {
        String welcome = "Hi! I'm Ruby, How can I help you?";
        printMsg(welcome);
    }

    /**
     * Displays the goodbye message of the bot application.
     */
    public void showGoodbye() {
        this.sc.close();
        printMsg("Okay, bye! Hope to see you again :)");
    }

    /**
     * Prints the message that is parsed into this method with dividers.
     *
     * @param msg a String containing the message to be printed.
     */
    public void printMsg(String msg) {
        String divider = "---------------------------------------------------------";
        System.out.println(divider);
        System.out.println(msg);
        System.out.println(divider);
    }

}
