package arthur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles interactions with the user.
 */
public class Ui {
    BufferedReader io;

    /**
     * Constructor for Ui object.
     */
    public Ui() {
        this.io = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Adds design above and below the given text.
     * @param text The string to be placed between the design
     */
    public void printFormat(String text) {
        String design = "_";
        System.out.println(design.repeat(50) + "\n"
                + text + "\n" + design.repeat(50));
    }

    /**
     * Displays the logo and greetings.
     */
    public void showWelcome() {
        String logo = " / \\   _ |_ |_  | |  _  \n"
                + "/---\\ |  |_ | | |_| |  ";
        printFormat("Greetings from\n" + logo
                + "\n" + "How may I assist you today?");
    }

    /**
     * Reads user inputs
     * @return A string version of the user input
     */
    public String readInst() {
        String temp = null;
        try {
            temp = io.readLine();
            temp = temp.toLowerCase();
        } catch (IOException E) {
            printFormat("Sorry, there seems to be an issue with the IO."
                    + "\n" + "Please restart and try again");
        }
        return temp;
    }
}
